package com.pinecone.summer.prototype;

import com.pinecone.framework.system.prototype.Ally;
import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONObject;

public interface Wizard extends Ally, Citizen {
    @Override
    default String vocationName(){
        return this.getClass().getSimpleName();
    }

    String prototypeName();

    String getTitle();

    JSONObject getModularConfig();

    String getModularRole();

    int getModularRoleIndex();

    JSONArray getMyNaughtyGenies();

    String getWizardCommand();


    /***  Parent getter methods ***/
    Connectson getConnection();

    ServiceSystem getServiceSystem();

    ConnectDispatcher getDispatcher();
}
