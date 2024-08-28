package com.sauron.saurye.wizard.User.NovelGrimoire;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

public class NovelGrimoireControl extends NovelGrimoire implements JSONBasedControl {
    public NovelGrimoireControl( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception{
        super.defaultGenie();
    }
}
