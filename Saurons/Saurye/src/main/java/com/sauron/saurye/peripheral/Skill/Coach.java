package com.sauron.saurye.peripheral.Skill;

import com.sauron.saurye.system.legacy.Prototype;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.Saurye;
import com.sauron.saurye.system.prototype.PeripheralBus;

public interface Coach extends PeripheralBus {
    default Class childType() {
        try {
            return Class.forName( Prototype.namespace( this ) + "." + Prototype.namespaceNode( this ) );
        } catch ( ClassNotFoundException e ){
            return null;
        }
    }

    String prototypeName();

    Saurye host() ;

    String getNodeNamespace();

    Skill learned( String szSkillName );

    Skill learned( String szSkillName, JSONObject properties );

    Skill learned( String szSkillName, String key, Object val );
}
