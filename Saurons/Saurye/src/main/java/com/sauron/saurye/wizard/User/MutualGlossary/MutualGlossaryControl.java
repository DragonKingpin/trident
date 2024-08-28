package com.sauron.saurye.wizard.User.MutualGlossary;

import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;
import com.pinecone.framework.util.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class MutualGlossaryControl extends MutualGlossary implements JSONBasedControl {
    public MutualGlossaryControl( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void beforeGenieInvoke() throws Exception {
        super.beforeGenieInvoke();
        this.mszCurrentUser = this.currentUser().username();
    }

    @Override
    public void defaultGenie() throws Exception{
        super.defaultGenie();
    }

    public void collectWordGlossary() throws SQLException,IOException{
        boolean bRes = true;
        String szClassId = $_GSC().optString( "class_id");
        if( this.mysql().countFromTable(
                String.format( "SELECT COUNT(*) FROM %s WHERE username = '%s' AND classid = '%s' ",
                        this.alchemist().user().pamphlet().tabCollectionNS(),this.mszCurrentUser,
                        szClassId
                ) )>0
        ){
            this.alert( "已收藏该单词本", 0, -1 );
        }
        if( this.mysql().countFromTable(
                String.format("SELECT COUNT(*) FROM %s WHERE classid = '%s'",
                        this.alchemist().user().pamphlet().tabPamphletsNS(),szClassId)
        )!=1){
           this.alert("不存在该单词本",0,-1);
        }

        JSONObject wordData = new JSONMaptron();
        wordData.put( "username",this.mszCurrentUser );
        wordData.put("classid",szClassId );
        bRes &= this.mysql().insertWithArray( this.alchemist().user().pamphlet().tabCollectionNS(), wordData.getMap() ) > 0;

        this.checkResult( bRes, null,
                this.spawnActionQuerySpell("wordList" )+"&class_id=" + $_GSC().optString("class_id" )
        );
    }

    /** Word **/

}