package com.sauron.saurye.wizard.Admin.AdminMutualWordDepositor;

import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;
import com.sauron.saurye.peripheral.Skill.MVC.DMLayerSkill;

import java.io.IOException;
import java.sql.SQLException;

public class AdminMutualWordDepositorControl extends AdminMutualWordDepositor implements JSONBasedControl {
    public AdminMutualWordDepositorControl( ArchConnection connection  ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

    public void appendNewWord() throws IOException,SQLException {
        JSONObject $_SPOST = this.$_POST( true );
        JSONObject en2ch_data = $_SPOST.subJson( new String[]{ "en_word", "cn_simple_mean" } );

        String szNewWord = $_SPOST.optString("en_word");
        if( this.mysql().countFromTable(
                String.format(
                        "SELECT COUNT(*) FROM %s WHERE `en_word` = '%s'" ,
                        this.alchemist().mutual().word().tabWordNS(), szNewWord )
        ) > 0 ){
            this.alert( "'"  + szNewWord +"' has been existed.", 0, -1 );
        }

        boolean bRes = this.mysql().insertWithArray( this.alchemist().mutual().dict().tabEn2CnNS(), en2ch_data.getMap() ) > 0;
        $_SPOST.remove( "cn_simple_mean" );
        bRes &= this.mysql().insertWithArray( this.alchemist().mutual().word().tabWordNS(), $_SPOST.getMap() ) > 0;

        this.checkResult( bRes, null, this.spawnActionQuerySpell() );
    }

    public void deleteOneWord() throws IOException,SQLException {
//        this.checkResult(
//                this.deleteOneWord( null )
//        );
    }

    public boolean deleteOneWord( String szID ) throws IOException, SQLException {
        /* MySQL Tagger is on **/
        return false;
        //return this.coach().control().simpleDeleteSingleObject( "id", szID, this.alchemist().mutual().word().tabWord() );
    }

    public void modifyOneWord() throws IOException, SQLException {
        JSONObject $_SPOST = this.$_POST( true );

        JSONObject condition  = $_SPOST.subJson( "en_word" );
        String szOldWord = $_SPOST.optString("en_word"), szNewWord = $_SPOST.optString("en_word_new");
        if( !szOldWord.equals(szNewWord) ){
            $_SPOST.put( "en_word", szNewWord );
        }
        JSONObject en2ch_data = $_SPOST.subJson( new String[]{ "en_word", "cn_simple_mean" } );
        $_SPOST.remove("en_word_new");

        boolean bRes = this.mysql().updateWithArray( this.alchemist().mutual().dict().tabEn2CnNS(), en2ch_data.getMap(), condition.getMap() ) > 0;
        $_SPOST.remove( "cn_simple_mean" );
        bRes &= this.mysql().updateWithArray( this.alchemist().mutual().word().tabWordNS(), $_SPOST.getMap(), condition.getMap() ) > 0;

        this.checkResult( bRes );
    }

    public void wordMassDelete() throws IOException, SQLException {
        DMLayerSkill.commonMassDelete( this, "id[]", "deleteOneWord" );
    }

}
