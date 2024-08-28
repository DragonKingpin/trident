package com.sauron.saurye.peripheral.Equipment;

import com.sauron.saurye.system.legacy.Prototype;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.Saurye;
import com.sauron.saurye.system.prototype.PeripheralBus;

public interface EquipmentPeddler extends PeripheralBus {
    enum Type {
        T_UI
    }

    default Class childType() {
        try {
            return Class.forName( Prototype.namespace( this ) + "." + Prototype.namespaceNode( this ) );
        } catch ( ClassNotFoundException e ){
            return null;
        }
    }

    String prototypeName();

    Type type();

    JSONObject getPrivateConfig();

    Saurye host();

    String getEquipmentNS();

    Equipment purchase( String szName );

    Equipment purchase( String szName, JSONObject additions );

}
