package com.sauron.saurye.wizard.User.GeniusTranslator;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class GeniusTranslator extends PredatorGenieBottle implements Wizard {
    public GeniusTranslator( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }
}
