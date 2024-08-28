package com.sauron.saurye.wizard.User.PersonalSentences;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.pinecone.framework.util.json.JSONArray;
import com.sauron.saurye.elements.user.Pamphlet.Pamphlet;
import com.sauron.saurye.elements.user.Pamphlet.PamphletIncarnation;
import com.sauron.saurye.system.PredatorGenieBottle;

public class PersonalSentences extends PredatorGenieBottle implements Wizard, PamphletIncarnation {
    public static JSONArray sortStream = null;

    protected String mszCurrentUser = "";

    public PersonalSentences( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

    @Override
    public Pamphlet protoIncarnated() {
        return this.alchemist().user().pamphlet().sentence();
    }

}