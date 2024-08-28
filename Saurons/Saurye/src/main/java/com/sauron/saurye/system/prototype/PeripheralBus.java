package com.sauron.saurye.system.prototype;

import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.framework.util.json.JSONObject;

public interface PeripheralBus {
    Class childType();

    default JSONObject nodeProperty(){
        return new JSONMaptron();
    }
}
