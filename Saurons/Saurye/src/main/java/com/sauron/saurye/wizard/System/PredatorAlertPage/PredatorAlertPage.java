package com.sauron.saurye.wizard.System.PredatorAlertPage;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class PredatorAlertPage extends PredatorGenieBottle implements Wizard {
    public PredatorAlertPage( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}