package com.sauron.saurye.system.properties;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.authority.AuthorityProperties;
import com.sauron.saurye.system.Saurye;

public class Properties {
    private Saurye mParentSystem;
    private JSONObject            mGlobalPropertiesConf;
    private Paginate              mPaginate;
    private AuthorityProperties   mAuthority;

    private void javaifyProperties() {
        this.mPaginate  = new Paginate( this.mGlobalPropertiesConf.optJSONObject( "Paginate" ) );
        this.mAuthority = new AuthorityProperties( this.mParentSystem );
    }

    public Properties( Saurye predator ) {
        this.mParentSystem         = predator;
        this.mGlobalPropertiesConf = this.mParentSystem.getGlobalPropertiesConfig();
        this.javaifyProperties();
    }

    public Saurye parent(){
        return this.mParentSystem;
    }

    public boolean  hasOwnProperty( Object key ) {
        return this.mGlobalPropertiesConf.hasOwnProperty( key );
    }

    public JSONObject getJsonGlobalProperties() {
        return this.mGlobalPropertiesConf;
    }

    public Paginate             paginate() {
        return this.mPaginate;
    }

    public AuthorityProperties  authority() { return this.mAuthority; }

}
