package com.sauron.saurye.peripheral.Skill;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.prototype.Peripheral;

import java.util.Map;

public interface Skill extends Peripheral {
    String prototypeName();

    Coach getCoach();

    default Skill setProperty( String key, Object val ){ return this; }

    default Skill setProperty( JSONObject properties ){
        for( Object row : properties.entrySet() ){
            Map.Entry kv = (Map.Entry) row;
            this.setProperty( (String) kv.getKey(), kv.getValue() );
        }
        return this;
    }

    default JSONObject property(){
        return null;
    }

    default String nodeName() {
        return this.getClass().getSimpleName();
    }
}
