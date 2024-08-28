package com.sauron.saurye.wizard.User.NovelExhibitor;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;


public class NovelExhibitorControl extends NovelExhibitor implements JSONBasedControl {
    public NovelExhibitorControl( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception{
        super.defaultGenie();
    }

}
