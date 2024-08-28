package com.sauron.saurye.wizard.User.GeniusTranslator;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class GeniusTranslatorControl extends GeniusTranslator implements JSONBasedControl {
    public GeniusTranslatorControl( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }
}