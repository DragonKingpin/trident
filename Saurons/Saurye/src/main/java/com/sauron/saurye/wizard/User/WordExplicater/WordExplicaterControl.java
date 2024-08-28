package com.sauron.saurye.wizard.User.WordExplicater;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class WordExplicaterControl extends WordExplicater implements JSONBasedControl {
    public WordExplicaterControl( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

}