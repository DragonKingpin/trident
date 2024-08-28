package com.sauron.saurye.wizard.Public.SiteNavigation;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorGenieBottle;

public class SiteNavigation extends PredatorGenieBottle implements Wizard {
    public SiteNavigation( ArchConnection connection ){
        super(connection);
    }

    public String prototypeName(){
        return this.getClass().getSuperclass().getSimpleName();
    }

}
