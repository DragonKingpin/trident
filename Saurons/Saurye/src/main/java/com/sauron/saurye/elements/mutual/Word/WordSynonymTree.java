package com.sauron.saurye.elements.mutual.Word;

import com.pinecone.framework.util.StringUtils;
import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONArraytron;
import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.framework.util.math.Vectorizer;
import com.sauron.saurye.elements.prototype.EpitomeCrystal;
import com.sauron.saurye.peripheral.Skill.Util.MathHelper;

import java.sql.SQLException;
import java.util.*;

public class WordSynonymTree extends AbstractWordTree implements EpitomeCrystal {
    public WordSynonymTree(Word stereotype ){
        super( stereotype );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Word stereotype() {
        return (Word) this.mStereotype;
    }


    private String  mszEnWord;
    private String  mszRootId;

    public WordSynonymTree apply( String szEnWord ){
        this.mszEnWord = szEnWord;
        return this;
    }

    private void eval_basic( JSONArray that ) throws SQLException {
        String szId      = this.mszRootId + "_basic";

        JSONArray jChildren = new JSONArraytron();

        JSONArray synonyms = this.mysql().fetch(
                String.format( "SELECT %s FROM %s AS tAssoc WHERE tAssoc.`en_word` = '%s' AND `s_association` = 'Synonym'",
                        StringUtils.sequencify( new String[]{ "`en_word`","`en_pair`","`s_association`", "`cn_annotate`" } , ",", "tAssoc." ),
                        this.stereotype().tabSemanticAssocNS()
                        ,this.mszEnWord
                )
        );

        TreeMap<String, JSONArray > s_map = new TreeMap<>();

        for ( int i = 0; i < synonyms.length(); i++ ) {
            JSONObject row = synonyms.optJSONObject( i );
            String szKey = row.optString("cn_annotate");
            if( !s_map.containsKey( szKey ) ){
                s_map.put( szKey, new JSONArraytron() );
            }

            s_map.get( row.optString("cn_annotate") ).put( row.optString("en_pair") );
        }

        int i = 0;
        for( Map.Entry row : s_map.entrySet() ){
            String szPartId      = this.idMaker( szId, i );

            JSONArray arrays         = (JSONArray) row.getValue();
            JSONArray jWordsChildren = new JSONArraytron();
            for ( int j = 0; j < arrays.length(); j++ ) {
                String szWord = arrays.optString(j);

                jWordsChildren.put( this.spawnNode( this.idMaker( szPartId, j ), szWord, new JSONArraytron() ) );
            }

            String szDef = (String) row.getKey();
            if( szDef.isEmpty() ){
                szDef = "其他";
            }

            jChildren.put(  this.spawnNode( szPartId, szDef , jWordsChildren ) );
            i++;
        }

        //this.eval_prof_each( jChildren, szId );
        JSONObject jTree = this.spawnNode( szId, "基本", jChildren );
        that.put( jTree );
    }

    private TreeMap<String, JSONArray > getKeyWordMap( JSONArray tabIndexCnEn ){
        TreeMap<String, JSONArray > s_map = new TreeMap<>();

        for ( int i = 0; i < tabIndexCnEn.length(); i++ ) {
            JSONObject row = tabIndexCnEn.optJSONObject( i );
            String szKey = row.optString("cn_word" );
            if( !s_map.containsKey( szKey ) ){
                s_map.put( szKey, new JSONArraytron() );
            }

            s_map.get( row.optString("cn_word" ) ).put( row );
        }
        return s_map;
    }

    private void eval_latent_high_p( JSONArray that, String szUpperId ) throws SQLException {
        String szRId      = this.idMaker( szUpperId, 0 );

        JSONArray self_esti_sames = this.mysql().fetch(
                String.format( "SELECT tEnCnS.`en_word`, tEnCn.`cn_word`, tEnCn.`m_property` FROM (" +
                                "  SELECT tEnCn.`cn_word`, tEnCn.`m_property` FROM %s AS tEnCn WHERE tEnCn.`en_word` = '%s' " +
                                ") AS tEnCn LEFT JOIN %s AS tEnCnS ON tEnCn.`cn_word` = tEnCnS.`cn_word` ",
                        this.owned().dict().tabEnCnIndexNS()
                        ,this.mszEnWord,  this.owned().dict().tabEnCnIndexNS()
                )
        );

        TreeMap<String, JSONArray > s_map = this.getKeyWordMap( self_esti_sames );


        //Debug.trace( s_map );

        JSONArray jChildren = new JSONArraytron();

        int i = 0;
        for( Map.Entry row : s_map.entrySet() ){
            String szPartId      = this.idMaker( szRId, i );

            JSONArray arrays         = (JSONArray) row.getValue();
            JSONArray jWordsChildren = new JSONArraytron();
            for ( int j = 0; j < arrays.length(); j++ ) {
                JSONObject each_word = arrays.optJSONObject(j);

                String szKwId = this.idMaker( szPartId, j );

                JSONArray jDetails = new JSONArraytron();
                jDetails.put( this.spawnKVNode( this.idMaker( szKwId, 0 ), "PoS", each_word.optString( "m_property" ) ) );

                jWordsChildren.put( this.spawnNode( szKwId, each_word.optString( "en_word" ), jDetails ) );
            }

            jChildren.put(  this.spawnNode( szPartId, (String) row.getKey(), jWordsChildren ) );
            i++;
        }


        JSONObject jTree = this.spawnNode( szRId, "关键字", jChildren );
        that.put( jTree );

        this.eval_latent_esti_p( that, szUpperId, s_map );
    }

    private void eval_latent_esti_p( JSONArray that, String szUpperId, TreeMap<String, JSONArray > keyWordsMap ) throws SQLException {
        String szRId      = this.idMaker( szUpperId, 1 );

        Set<String > kwSet = new HashSet<>();
        for( Map.Entry row : keyWordsMap.entrySet() ){
            String szKey = (String) row.getKey();
            Vector<String > cnKeys = this.master().host().textBasicProcessor().cn_segmenting_important( szKey );

            kwSet.addAll(cnKeys);
        }

        TreeMap<String, JSONArray > s_map = new TreeMap<>();

        for ( String szKw : kwSet ) {
            if( !s_map.containsKey( szKw ) ){
                s_map.put( szKw, new JSONArraytron() );
            }

            JSONArray latents = this.mysql().fetch(
                    String.format( "SELECT tEnCn.`en_word`, tEnCn.`cn_word`, tEnCn.`m_property` FROM %s AS tEnCn WHERE tEnCn.`cn_word` LIKE '%s%%' ",
                            this.owned().dict().tabEnCnIndexNS()
                            ,szKw
                    )
            );

            TreeMap<Double, JSONObject > weightMap = new TreeMap<>();
            for ( int i = 0; i < latents.length(); i++ ) {
                JSONObject row = latents.optJSONObject( i );

                Vector<String > latentSeg = this.master().host().textBasicProcessor().cn_segmenting_important( row.optString("cn_word") );

                if( !latentSeg.isEmpty() ){
                    Vectorizer<String > vectorized = new Vectorizer<String>( latentSeg, new Vector<String >(kwSet) );

                    double nWeight = 1 - MathHelper.getCosineSimilarity (
                            vectorized.getResult().get(0), vectorized.getResult().get(1)
                    );
                    weightMap.put( nWeight + i * 1e-7 , row );

                    row.put( "weight", nWeight );
                }
            }


            int i = 0;
            for ( Map.Entry row : weightMap.entrySet() ){
                s_map.get( szKw ).put( row.getValue() );
                if( i++ > 30 ){
                    break;
                }
            }


            //Debug.trace( s_map );

        }


        JSONArray jChildren = new JSONArraytron();

        int i = 0;
        for( Map.Entry row : s_map.entrySet() ){
            String szPartId      = this.idMaker( szRId, i );

            JSONArray arrays         = (JSONArray) row.getValue();
            JSONArray jWordsChildren = new JSONArraytron();
            for ( int j = 0; j < arrays.length(); j++ ) {
                JSONObject each_word = arrays.optJSONObject(j);

                JSONArray jDetails = new JSONArraytron();
                String szKwId = this.idMaker( szPartId, j );

                jDetails.put( this.spawnKVNode( this.idMaker( szKwId, 0 ), "PoS", each_word.optString( "m_property" ) ) );
                jDetails.put( this.spawnKVNode( this.idMaker( szKwId, 1 ), "参考义", each_word.optString( "cn_word" ) ) );
                jDetails.put( this.spawnKVNode( this.idMaker( szKwId, 2 ), "权",  String.format( "%.5f", each_word.optDouble( "weight" ) ) ) );

                jWordsChildren.put( this.spawnNode( szKwId, each_word.optString( "en_word" ), jDetails ) );
            }

            jChildren.put(  this.spawnNode( szPartId, (String) row.getKey(), jWordsChildren ) );
            i++;
        }

        JSONObject jTree = this.spawnNode( szRId, "近似", jChildren );
        that.put( jTree );
    }

    private void eval_latent( JSONArray that ) throws SQLException {
        String szId      = this.mszRootId + "_latent";

        JSONArray jChildren = new JSONArraytron();

        this.eval_latent_high_p( jChildren, szId );

        JSONObject jTree = this.spawnNode( szId, "潜在", jChildren );
        that.put( jTree );
    }

    public JSONObject eval()  throws SQLException {
        JSONObject jTree = new JSONMaptron();
        this.mszRootId = this.mszEnWord + "_root";
        jTree.put( "name", this.mszEnWord );
        jTree.put( "id", this.mszRootId );
        JSONArray jChildren = new JSONArraytron();

        this.eval_basic( jChildren );
        this.eval_latent( jChildren );

        jTree.put( "children", jChildren );

        return jTree;
    }
}
