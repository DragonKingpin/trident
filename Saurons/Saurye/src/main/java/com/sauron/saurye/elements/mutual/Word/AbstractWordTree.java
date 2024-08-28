package com.sauron.saurye.elements.mutual.Word;

import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONArraytron;
import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.elements.mutual.EpitomeSharded;

public abstract class AbstractWordTree extends EpitomeSharded {
    public AbstractWordTree(Word stereotype ){
        super( stereotype );
    }

    protected JSONObject applyNode(JSONObject that, String szId, String szName, JSONArray children ) {
        that.put( "id",   szId   );
        that.put( "name", szName );
        that.put( "children", children );
        return that;
    }

    protected JSONObject applyNode( JSONObject that, String szId, String szName ) {
        return this.applyNode( that, szId, szName, new JSONArraytron() );
    }

    protected JSONObject spawnNode( String szId, String szName, JSONArray children ) {
        JSONObject that = new JSONMaptron();
        return this.applyNode( that, szId, szName, children );
    }

    protected JSONObject spawnNode( String szId, String szName ) {
        JSONObject that = new JSONMaptron();
        return this.applyNode( that, szId, szName );
    }

    protected JSONObject spawnKVNode( String szId, String szKey, Object val ) {
        JSONObject that = new JSONMaptron();
        if( val instanceof JSONArray ){
            return this.applyNode( that, szId, szKey, (JSONArray) val );
        }
        JSONArray jPart = new JSONArraytron();
        jPart.put( this.spawnNode( this.idMaker( szId, 0 ), val.toString() ) );
        return this.applyNode( that, szId, szKey, jPart );
    }

    protected String idMaker( String szUpperId, int i ){
        return szUpperId + "_" + i;
    }
}
