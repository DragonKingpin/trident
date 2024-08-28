package com.sauron.saurye.wizard.User.PersonalFragments;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.pinecone.framework.util.json.JSONArray;
import com.sauron.saurye.system.PredatorGenieBottle;

public class PersonalFragments extends PredatorGenieBottle implements Wizard {
    public static JSONArray sortStream = null;

    protected String mszCurrentUser = "";

    public PersonalFragments ( ArchConnection session ) {
        super( session );
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}