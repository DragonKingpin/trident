package com.sauron.saurye.elements;

import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.system.prototype.Alchemist;

public interface PivotAlchemist extends Alchemist {
    String rootName();

    JSONObject elementPart ( String szElementPartName );

    JSONObject tableFields ( String szElementPartName );

    JSONObject assetFields ( String szElementPartName );
}
