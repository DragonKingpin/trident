package com.sauron.saurye.wizard.User.FragmentExplicater;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class FragmentExplicaterControl extends FragmentExplicater implements JSONBasedControl {
    public FragmentExplicaterControl( ArchConnection connection  ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }
}
