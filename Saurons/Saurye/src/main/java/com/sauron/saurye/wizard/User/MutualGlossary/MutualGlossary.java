package com.sauron.saurye.wizard.User.MutualGlossary;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class MutualGlossary extends PredatorGenieBottle implements Wizard {
    protected String mszCurrentUser = "";

    public MutualGlossary( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}