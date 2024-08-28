package com.sauron.saurye.wizard.Public.PredatorIndex;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class PredatorIndex extends PredatorGenieBottle implements Wizard {
    public PredatorIndex( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
