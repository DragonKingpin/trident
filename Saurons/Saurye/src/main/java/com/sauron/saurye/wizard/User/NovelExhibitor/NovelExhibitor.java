package com.sauron.saurye.wizard.User.NovelExhibitor;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class NovelExhibitor extends PredatorGenieBottle implements Wizard {
    public NovelExhibitor( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
