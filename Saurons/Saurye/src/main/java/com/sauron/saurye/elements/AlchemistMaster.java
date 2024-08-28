package com.sauron.saurye.elements;

import com.pinecone.framework.util.ReflectionUtils;
import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.elements.mutual.MutualAlchemist;
import com.sauron.saurye.elements.user.UserAlchemist;
import com.sauron.saurye.system.Saurye;
import com.sauron.saurye.system.legacy.Prototype;
import com.sauron.saurye.system.prototype.Alchemist;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class AlchemistMaster implements Alchemist {
    private JSONObject  mElementsConfig;
    private JSONObject  mElementsProto;
    private JSONObject  mElementsPart;
    private Saurye mHost;
    private String      mszTabFieldPrefix = null;
    private String      mszAssetsPrefix   = null;

    public AlchemistMaster( Saurye host ) {
        this.mHost = host;
        try {
            this.mElementsPart     = new JSONMaptron(
                    this.mHost.readFileContentAll(
                            this.mHost.getResourcesPath() +
                                    this.mHost.getSitesConfig().optString( this.getRootNamespace() )
                    )
            );
            this.mElementsProto    = this.mElementsPart.optJSONObject( this.getRootNamespace() );
            this.mElementsConfig   = this.mElementsPart.optJSONObject( "config" );
            this.mszTabFieldPrefix = this.mElementsConfig.optString( "JavaFieldTablePrefix" );
            this.mszAssetsPrefix   = this.mElementsConfig.optString( "JavaFieldAssetsPrefix" );
            this.spawnGlobalAlchemists();
        }
        catch ( IOException e ){
            e.printStackTrace();
            this.mElementsPart   = null;
            this.mElementsProto  = null;
            this.mElementsConfig = null;
        }
    }

    public Saurye host() {
        return this.mHost;
    }

    public String   tableName( String szSimpleTable ){
        return this.host().mysql().tableName( szSimpleTable );
    }

    public String getRootNamespace() {
        return Prototype.namespaceNode( this );
    }

    public JSONObject getElementsPart() {
        return this.mElementsPart;
    }

    public JSONObject getElementsProto() {
        return this.mElementsProto;
    }

    public JSONObject getElementsConfig() {
        return this.mElementsConfig;
    }

    public String getJavaFieldTablePrefix() {
        return this.mszTabFieldPrefix;
    }

    public String getJavaFieldAssetsPrefix() {
        return this.mszAssetsPrefix;
    }

    public void javaify ( String szPrefix, Object that, JSONObject proto ) {
        for( Object row : proto.entrySet() ){
            Map.Entry kv = (Map.Entry) row;
            Field field;
            try{
                field = that.getClass().getDeclaredField( szPrefix + kv.getKey().toString() );
            }
            catch ( NoSuchFieldException e ){
                field = null;
            }
            if( field != null ){
                ReflectionUtils.makeAccessible( field );
                try {
                    try {
                        field.set( that, kv.getValue() );
                    }
                    catch ( IllegalArgumentException e ){
                        e.printStackTrace();
                    }
                }
                catch ( IllegalAccessException e ){
                    throw new IllegalStateException(e); // This should never be happened.
                }
            }
        }
    }

    public void tableJavaify ( Object that, JSONObject proto ) {
        this.javaify( this.mszTabFieldPrefix, that, proto );
    }

    public void assetJavaify ( Object that, JSONObject proto ) {
        this.javaify( this.mszAssetsPrefix, that, proto );
    }

    public JSONObject jsonify ( String szPrefix, Object that ) {
        JSONObject jsonObject = new JSONMaptron();

        Field[] fields = that.getClass().getDeclaredFields();
        for( Field field : fields ){
            String szFieldName = field.getName();
            if( szFieldName.startsWith( szPrefix ) && field.getType() == String.class ){
                ReflectionUtils.makeAccessible( field );
                try{
                    jsonObject.put( szFieldName.substring( szPrefix.length() ), field.get( that ) );
                }
                catch ( IllegalAccessException e ){
                    e.printStackTrace();
                }
            }
        }

        return jsonObject;
    }

    public JSONObject tableJsonify( Object that ) {
        return this.jsonify( this.mszTabFieldPrefix, that );
    }




    private MutualAlchemist mMutualAlchemist;

    private UserAlchemist mUserAlchemist;

    private void spawnGlobalAlchemists(){
        this.mMutualAlchemist = new MutualAlchemist( this );
        this.mUserAlchemist   = new UserAlchemist( this );
    }

    public MutualAlchemist mutual(){
        return this.mMutualAlchemist;
    }

    public UserAlchemist user() {
        return this.mUserAlchemist;
    }
}
