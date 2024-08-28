package com.sauron.saurye.wizard.User.ReciteCenter;

import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.framework.util.Randomium;
import com.sauron.saurye.peripheral.Skill.Util.DateHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ReciteCenterControl extends ReciteCenter implements JSONBasedControl {
    public ReciteCenterControl( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void beforeGenieInvoke() throws Exception {
        super.beforeGenieInvoke();
        this.mszCurrentUser = this.currentUser().username();
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

    public void studyThisBook() throws SQLException,IOException {
        String szClass_id = $_GSC().optString("class_id");
        Map<String, String > condition = new TreeMap<>();
        Map<String, String > data      = new TreeMap<>();
        condition.put( "p_now_plan", "true" );
        condition.put( "username",this.mszCurrentUser);
        data.put( "p_now_plan","false" );
        boolean bRs = this.mysql().updateWithArray( this.alchemist().user().glossary().tabRecitePlanNS(),data,condition ) > 0;
        condition.clear();
        data.clear();
        data.put("p_now_plan","true");
        condition.put( "username",this.mszCurrentUser);
        condition.put("p_now_plan","false");
        condition.put( "classid",szClass_id );

        this.checkResult(   bRs & this.mysql().updateWithArray( this.alchemist().user().glossary().tabRecitePlanNS(),data,condition )>0 );
    }

    public void setRecitedPlan() throws SQLException,IOException{
        String szClassID = $_GSC().optString("class_id");
        JSONObject dataMap = $_GSC().subJson(new String[]{ "p_word" });
        Map<String, String > condition = new TreeMap<>();
        condition.put("classid",szClassID);
        condition.put("username",this.mszCurrentUser);
        this.checkResult( this.mysql().updateWithArray(this.alchemist().user().glossary().tabRecitePlanNS(),dataMap.getMap(),condition)>0);
    }

    public void startStudyBook() throws SQLException,IOException{
      String szClassID = $_GSC().optString("class_id");
      String szG_name  = $_GSC().optString("g_name");
      String szUsernameProto = $_GSC().optString("username");
      JSONObject glossaryData = new JSONMaptron();
      String szNewClassId = null;
      boolean bRes = true;

      boolean bIsUser = this.mysql().countFromTable(String.format("SELECT COUNT(*) FROM %s WHERE `classid`='%s' AND `username` = '%s'"
              ,this.alchemist().user().pamphlet().tabPamphletsNS(),szClassID,this.mszCurrentUser) )>0;


        if( this.mysql().countFromTable(
                String.format(
                        "SELECT COUNT(*) FROM %s WHERE  `classid` = '%s' AND `g_authority` = 'public'" ,
                        this.alchemist().user().pamphlet().tabPamphletsNS(),szClassID)
        ) == 0&&!bIsUser){
            this.alert( "没有该单词本", 0, -1 );
        }
        else{
            if( !bIsUser ){
                if(this.mysql().countFromTable(
                        String.format(
                                "SELECT COUNT(*) FROM %s WHERE  `classid` = '%s' AND username = '%s'" ,
                                this.alchemist().user().pamphlet().tabCollectionNS(),szClassID,this.mszCurrentUser)
                ) == 0){
                    JSONObject wordData = new JSONMaptron();
                    wordData.put( "username",this.mszCurrentUser );
                    wordData.put("classid",szClassID );
                    this.mysql().insertWithArray( this.alchemist().user().pamphlet().tabCollectionNS(), wordData.getMap() );
                }
                if(this.mysql().countFromTable(String.format("SELECT COUNT(*) FROM %s WHERE   username = '%s' AND g_name='%s'",
                        this.alchemist().user().pamphlet().tabPamphletsNS(),this.mszCurrentUser,szG_name)) >0){
                    this.alert("已有"+szG_name+"该单词本",0,-1 );
                }
                else{
                    szNewClassId = new Randomium().nextString(15 );
                    glossaryData.put( "classid", szNewClassId );
                    glossaryData.put( "username", this.mszCurrentUser );
                    glossaryData.put("g_state","reciting");
                    glossaryData.put("g_note","");
                    glossaryData.put("g_create_data",DateHelper.formatYMD());
                    glossaryData.put("g_level","Normal");
                    glossaryData.put("g_name",szG_name);
                    glossaryData.put("g_img_href","");
                    this.mysql().insertWithArray( this.alchemist().user().pamphlet().tabPamphletsNS(), glossaryData.getMap() );
                    glossaryData.clear();
                    glossaryData.put( "classid",szNewClassId);
                    bRes &= this.mysql().execute( String.format(
                            "INSERT INTO %s ( `en_word`, `g_note`, `d_sort_id`, `d_similar_w`,  `classid`,  `username`, `d_add_date` ) " +
                                    " ( SELECT `en_word`, `g_note`, `d_sort_id`, `d_similar_w`,  '%s' AS `classid`,  '%s' AS `username`, '%s' AS `d_add_date` " +
                                    "   FROM %s WHERE `classid` = '%s' AND `username` = '%s'  ORDER BY `id` ASC ) " ,
                            this.alchemist().user().glossary().tabWordsNS(), szNewClassId  , this.mszCurrentUser, DateHelper.formatYMD(),
                            this.alchemist().user().glossary().tabWordsNS(), szClassID, szUsernameProto
                    ) ) > 0;
                }
            }
            else{
                if(this.mysql().countFromTable(String.format("SELECT COUNT(*) FROM %s WHERE  `classid` = '%s' AND username = '%s' AND g_state = 'reciting'"
                        ,this.alchemist().user().pamphlet().tabPamphletsNS(),szClassID,this.mszCurrentUser) )>0){
                    this.alert("该单词本正在背诵",0,-1 );
                }
                else{
                    JSONObject dataMap = new JSONMaptron();
                    JSONObject condition = new JSONMaptron();
                    dataMap.put("g_state","reciting");
                    condition.put("g_state","studying");
                    condition.put("classid",szClassID);
                    this.mysql().updateWithArray(this.alchemist().user().pamphlet().tabPamphletsNS(),dataMap.getMap(),condition.getMap());
                    glossaryData.put("classid",szClassID);

                }
            }
            glossaryData.put("p_word",$_GSC().optString("p_word"));
            glossaryData.put("p_now_plan","false");
            glossaryData.put("username",this.mszCurrentUser);
            glossaryData.put("p_create_date",DateHelper.formatYMD());
            this.mysql().insertWithArray( this.alchemist().user().glossary().tabRecitePlanNS(),glossaryData.getMap() );

            this.checkResult(
                    bRes, null, this.spawnActionQuerySpell( "planList" )
            );
        }
    }

    public void deleteStudyPlan() throws SQLException,IOException{
//        String szClassID = $_GSC().optString("class_id");
//        if(szClassID!=null){
//            JSONObject dataMap = new JSONMaptron();
//            JSONObject wordDataMap = new JSONMaptron();
//            Map<String, String > condition = new TreeMap<>();
//            String date = null;
//            dataMap.put("g_state","studying");
//            wordDataMap.put("p_recite_date","1970-1-1");
//            wordDataMap.put("p_recite_degree",0);
//            condition.put("classid",szClassID);
//            condition.put("username",this.mszCurrentUser);
//            this.mysql().updateWithArray( this.alchemist().user().pamphlet().tabPamphletsNS(),dataMap.getMap(),condition );
//            this.mysql().updateWithArray( this.alchemist().user().glossary().tabWordsNS(),wordDataMap.getMap(),condition);
//            this.mysql().deleteWithArray( this.alchemist().user().glossary().tabRecitePlanNS(),condition);
//        }
    }
}