package com.sauron.saurye.wizard.User.WordExplicater;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class WordExplicater extends PredatorGenieBottle implements Wizard {
    public WordExplicater( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
