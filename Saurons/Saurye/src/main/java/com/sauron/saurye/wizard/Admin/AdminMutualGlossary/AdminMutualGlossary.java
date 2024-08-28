package com.sauron.saurye.wizard.Admin.AdminMutualGlossary;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class AdminMutualGlossary extends PredatorGenieBottle implements Wizard {
    public AdminMutualGlossary( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }
}