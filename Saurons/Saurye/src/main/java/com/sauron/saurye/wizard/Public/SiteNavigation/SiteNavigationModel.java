package com.sauron.saurye.wizard.Public.SiteNavigation;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.system.prototype.JasperModifier;

@JasperModifier
public class SiteNavigationModel extends SiteNavigation implements Pagesion {
    public SiteNavigationModel( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void beforeGenieInvoke() throws Exception {
        super.beforeGenieInvoke();
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
    }


}