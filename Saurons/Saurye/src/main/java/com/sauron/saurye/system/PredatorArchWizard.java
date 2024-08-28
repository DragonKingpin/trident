package com.sauron.saurye.system;

import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.ArchPageson;

public abstract class PredatorArchWizard extends ArchPageson {
    public Saurye system(){
        return (Saurye)(this.mParentSystem instanceof Saurye ? this.mParentSystem : null);
    }

    public JSONObject getWizardProto( String prototypeName ) {
        return this.system().getWizardsConfig().getJSONObject( prototypeName );
    }

    public PredatorArchWizard ( ArchConnection session ) {
        super( session );
    }

    public PredatorArchWizard () {
        super();
    }

    /** Role **/
    public int queryRoleIndex(String szRole ){
        return PredatorModularRoleInterpreter.interpret( szRole );
    }


    /** Wizard Archetype **/
    public String getTitle(){
        return this.getModularConfig().getString("title");
    }

    public JSONObject getModularConfig(){
        return this.getWizardProto( this.prototypeName() );
    }

    public String getModularRole(){
        return this.getModularConfig().getString("role");
    }

    public int getModularRoleIndex(){
        return this.queryRoleIndex( this.getModularRole() );
    }

    public JSONArray getMyNaughtyGenies() {
        return this.getModularConfig().optJSONArray("myNaughtyGenies");
    }
}
