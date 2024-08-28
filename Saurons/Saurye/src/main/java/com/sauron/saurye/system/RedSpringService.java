package com.sauron.saurye.system;

import java.io.IOException;
import java.nio.file.Path;

import com.pinecone.framework.system.executum.Processum;
import com.pinecone.hydra.servgram.Servgram;
import com.pinecone.summer.spring.Springron;

public class RedSpringService extends Springron {
    public RedSpringService(String szName, Processum parent, String[] springbootArgs ) {
        super( szName, parent, springbootArgs );
    }

    public RedSpringService( String szName, Processum parent ) {
        super( szName, parent );
    }

    @Override
    protected void loadConfig() {
        this.mServgramList     = this.getAttachedOrchestrator().getSectionConfig().getChild( Servgram.ConfigServgramsKey );
        Object dyServgramConf  = this.mServgramList.get( this.gramName() );
        if( dyServgramConf instanceof String ) {
            try{
                this.mServgramConf = this.mServgramList.getChildFromPath( Path.of((String) dyServgramConf) );
            }
            catch ( IOException ignore ) {
                this.getLogger().info( "[Notice] Springboot will use the default config `application.yaml`." );
            }
        }
        else {
            this.mServgramConf = this.mServgramList.getChild( this.gramName() );
        }
    }

    @Override
    public void execute() throws Exception {
        super.execute();
    }
}