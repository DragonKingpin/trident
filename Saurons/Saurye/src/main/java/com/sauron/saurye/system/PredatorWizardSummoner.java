package com.sauron.saurye.system;

import com.pinecone.framework.util.json.JSONException;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.ArchWizardSummoner;

public class PredatorWizardSummoner extends ArchWizardSummoner {
    public PredatorWizardSummoner(ArchConnection connection ) {
        super(connection);
    }

    public String getWizardRoleName( String szNickName ){
        String szRole = "Public";
        JSONObject proto = null;

        try {
            proto = ((Saurye)this.mParentSystem).getWizardProto( szNickName );
            szRole = proto.getString("role");
        }
        catch (JSONException e1){
            return szRole;
        }

        return szRole;
    }

    @Override
    public String queryNamespace( String szNickName ){
        return this.mParentSystem.getWizardPackageName() + "." + this.getWizardRoleName(szNickName) + "." + szNickName;
    }
}
