package com.sauron.saurye.wizard.Public.undefined;

import com.pinecone.framework.util.Debug;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.JSONBasedControl;
import com.pinecone.summer.multiparts.MultipartException;
import com.pinecone.summer.multiparts.MultipartFile;

import java.util.Map;

public class undefinedControl extends undefined implements JSONBasedControl {
    public undefinedControl( ArchConnection connection  ){
        super(connection);
    }


    public void defaultGenie() throws Exception {

//        CommonsFileUploadDispatcher commonsFileUploadDispathcher = new CommonsFileUploadDispatcher( this.parent() );
//        CommonsMultipartResolver commonsMultipartResolver = commonsFileUploadDispathcher.getMultipartResolver();
        try{
            Map<String, MultipartFile > mR = this.$_FILES();
            Debug.trace( mR );
            //Debug.trace( ((CommonsMultipartFile)mR.getFile("fileA")).getStoragePath() );

            console.log ( this.$_GET  (true) );
            console.log ( this.$_POST (true) );
            console.log ( this.$_GSC  () );


        }
        catch ( MultipartException e ){
            e.printStackTrace();
        }


        Debug.trace( this.currentHttpMethod() );
        Debug.trace( this.$_POST(true) );
    }

    public void shit(){
        Debug.trace("Control Shit");
    }
}
