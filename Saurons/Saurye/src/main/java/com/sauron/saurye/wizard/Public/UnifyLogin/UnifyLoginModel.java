package com.sauron.saurye.wizard.Public.UnifyLogin;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.prototype.JasperModifier;

import java.sql.SQLException;

@JasperModifier
public class UnifyLoginModel extends UnifyLogin implements Pagesion {
    public UnifyLoginModel( ArchConnection connection ){
        super(connection);
    }

    @Override
    public void beforeGenieInvoke() throws Exception {
        super.beforeGenieInvoke();
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
        this.unifyLogin();
    }


    public void unifyLogin() throws SQLException {
        JSONObject $_SPOST = this.$_POST( true );

        if( this.mPageData.hasOwnProperty( "warningInfo" ) ) {
            $_SPOST.moveSubFrom( this.mPageData,"warningInfo" );
        }

        this.mPageData.put( "S_POST", $_SPOST );
    }

}