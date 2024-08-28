package com.sauron.saurye.wizard.User.EtymologyExplorer;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class EtymologyExplorer extends PredatorGenieBottle implements Wizard {
    public EtymologyExplorer( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }
}
