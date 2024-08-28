package com.sauron.saurye.peripheral.Equipment;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.prototype.Peripheral;

public interface Equipment extends Peripheral {
    String prototypeName();

    EquipmentPeddler.Type type();

    JSONObject property();

    Equipment enchant( JSONObject additions );

    Equipment enchant( String key, Object val );

    Object synthesis();

    Object mount( Object ats );

    default String nodeName() {
        return this.getClass().getSimpleName();
    }

}
