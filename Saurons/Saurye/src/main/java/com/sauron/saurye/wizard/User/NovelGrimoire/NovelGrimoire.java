package com.sauron.saurye.wizard.User.NovelGrimoire;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class NovelGrimoire extends PredatorGenieBottle implements Wizard {
    public NovelGrimoire( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
