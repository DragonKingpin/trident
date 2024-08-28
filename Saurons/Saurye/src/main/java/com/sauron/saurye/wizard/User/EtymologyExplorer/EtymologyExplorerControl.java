package com.sauron.saurye.wizard.User.EtymologyExplorer;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class EtymologyExplorerControl extends EtymologyExplorer implements JSONBasedControl {
    public EtymologyExplorerControl( ArchConnection connection  ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

}