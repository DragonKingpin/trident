package com.sauron.saurye.wizard.User.GeniusExplorer;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class GeniusExplorer extends PredatorGenieBottle implements Wizard {
    public GeniusExplorer( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }
}
