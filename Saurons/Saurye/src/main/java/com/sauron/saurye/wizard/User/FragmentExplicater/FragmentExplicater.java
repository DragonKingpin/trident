package com.sauron.saurye.wizard.User.FragmentExplicater;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class FragmentExplicater extends PredatorGenieBottle implements Wizard {
    public FragmentExplicater( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
