package com.sauron.saurye.wizard.Public.SiteNavigation;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class SiteNavigationControl extends SiteNavigation implements JSONBasedControl {
    public SiteNavigationControl( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }

}