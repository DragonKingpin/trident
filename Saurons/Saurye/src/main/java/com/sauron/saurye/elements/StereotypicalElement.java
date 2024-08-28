package com.sauron.saurye.elements;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.prototype.Element;

public interface StereotypicalElement extends Element {
    void javaify ( String szPrefix, Object that, JSONObject proto );
}
