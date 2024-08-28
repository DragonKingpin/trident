package com.sauron.saurye.system.authority;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.Saurye;

public class AuthorityProperties {
    private JSONObject                mAuthorityConfig    = null;
    private boolean                   mbVerifyUser        = true;
    private String                    mszDefaultUsername  = ""  ;
    private String                    mszUserTokenField   = ""  ;
    private int                       mnUserTokenAge            ;

    public AuthorityProperties ( Saurye host ) {
        this.mAuthorityConfig   = host.getServiceSystemConfig().optJSONObject( "AuthorityConfig" );
        this.mbVerifyUser       = this.mAuthorityConfig.optBoolean( "VerifyUser" );
        this.mszDefaultUsername = this.mAuthorityConfig.optString ( "DefaultUsername" );
        this.mszUserTokenField  = this.mAuthorityConfig.optString ( "UserTokenField" );
        this.mnUserTokenAge     = this.mAuthorityConfig.optInt    ( "UserTokenAge" );
    }

    public JSONObject getAuthorityConfig() {
        return this.mAuthorityConfig;
    }

    public boolean isVerifyUser() {
        return this.mbVerifyUser;
    }

    public String getDefaultUsername() {
        return this.mszDefaultUsername;
    }

    public String getUserTokenField() {
        return this.mszUserTokenField;
    }

    public int getUserTokenAge() {
        return this.mnUserTokenAge;
    }
}
