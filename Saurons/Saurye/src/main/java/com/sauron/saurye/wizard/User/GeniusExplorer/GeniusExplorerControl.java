package com.sauron.saurye.wizard.User.GeniusExplorer;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class GeniusExplorerControl extends GeniusExplorer implements JSONBasedControl {
    public GeniusExplorerControl( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

}