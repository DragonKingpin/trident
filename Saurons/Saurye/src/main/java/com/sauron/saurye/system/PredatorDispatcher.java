package com.sauron.saurye.system;

import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.*;

import javax.servlet.ServletException;
import java.io.IOException;

public class PredatorDispatcher extends ArchConnectDispatcher {
    protected JSONObject              mGlobalSafePublicContainer    =  null  ;
    protected JSONObject              mGlobalSafeGETContainer       =  null  ;
    protected JSONObject              mGlobalSafePOSTContainer      =  null  ;


    public PredatorDispatcher( ArchServiceSystem system, RouterType routerType ){
        super( system, routerType );
    }

    public Saurye predator(){
        return (Saurye) this.mArchServiceSystem;
    }


    public JSONObject $_GSC() {
        return this.getHttpEntityParser().requestMapJsonify( this.mConnection.$_REQUEST( true ), true );
    }

    public JSONObject $_GET  ( boolean bSafe ) {
        if( !bSafe ){
            return this.mConnection.$_GET();
        }
        return this.getHttpEntityParser().parseQueryString( this.mConnection.$_REQUEST().getQueryString(), true );
    }

    public JSONObject $_POST ( boolean bSafe ) {
        if( !bSafe ){
            return this.mConnection.$_POST();
        }
        return this.getHttpEntityParser().parseFormData( this.mConnection.$_REQUEST( true ), true );
    }




    @Override
    public void echoIndexPage() throws IOException, ServletException {
        this.mWizardSummoner.summonAndExecute( "PredatorIndex" );
    }

    @Override
    public void summonByQueryString() throws ServletException, IOException {
        switch ( this.mszWizardCommand ){
            case "index": {
                this.echoIndexPage();
                break;
            }
            default:{
                super.summonByQueryString();
                break;
            }
        }
    }

}
