package com.sauron.saurye.wizard.Admin.AdminMutualWordDepositor;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class AdminMutualWordDepositor extends PredatorGenieBottle implements Wizard {
    public AdminMutualWordDepositor( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
