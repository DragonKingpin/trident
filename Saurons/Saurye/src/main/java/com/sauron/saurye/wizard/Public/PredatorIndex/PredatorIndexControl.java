package com.sauron.saurye.wizard.Public.PredatorIndex;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;

import javax.servlet.ServletException;
import java.io.IOException;

public class PredatorIndexControl extends PredatorIndex implements JSONBasedControl {
    public PredatorIndexControl( ArchConnection connection  ){
        super(connection);
    }

    @Override
    public void dispatch() throws IOException, ServletException {

    }
}
