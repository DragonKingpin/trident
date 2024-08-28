package com.sauron.saurye.wizard.System.SystemCoven;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class SystemCoven extends PredatorGenieBottle implements Wizard {
    public SystemCoven( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }
}
