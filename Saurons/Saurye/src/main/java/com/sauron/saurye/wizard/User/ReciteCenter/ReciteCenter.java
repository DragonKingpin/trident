package com.sauron.saurye.wizard.User.ReciteCenter;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.pinecone.framework.util.json.JSONArray;
import com.sauron.saurye.system.PredatorGenieBottle;

public class ReciteCenter extends PredatorGenieBottle implements Wizard {
    public static JSONArray sortStream = null;

    protected String mszCurrentUser = "";

    public ReciteCenter( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}