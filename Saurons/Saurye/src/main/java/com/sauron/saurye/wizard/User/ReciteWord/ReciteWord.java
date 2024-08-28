package com.sauron.saurye.wizard.User.ReciteWord;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.pinecone.framework.util.json.JSONArray;
import com.sauron.saurye.system.PredatorGenieBottle;

public class ReciteWord extends PredatorGenieBottle implements Wizard {
    public static JSONArray sortStream = null;

    protected String mszCurrentUser = "";

    public ReciteWord( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
