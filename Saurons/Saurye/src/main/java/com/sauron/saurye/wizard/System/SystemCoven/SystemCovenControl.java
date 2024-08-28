package com.sauron.saurye.wizard.System.SystemCoven;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class SystemCovenControl extends SystemCoven implements JSONBasedControl {
    public SystemCovenControl( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }
}