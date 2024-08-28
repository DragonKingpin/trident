package com.pinecone.summer;

import com.pinecone.framework.unit.MultiValueMap;
import com.pinecone.summer.http.HttpMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class PostConnection extends ArchConnection {
    public PostConnection( ArchConnectDispatcher dispatcher ) {
        super( dispatcher );
    }

    protected PostConnection(ArchConnectDispatcher dispatcher, Connectiom connectiom ) {
        super( dispatcher, connectiom);
    }

    @Override
    protected ArchConnection apply( Connectiom connectiom ) {
        super.apply(connectiom);

        this.mCurrentHttpMethod            = HttpMethod.POST;
        this.mGETMapContainer              = this.mHttpEntityParser.parseQueryString  ( this.mConnectiom.request.getQueryString(), false );
        this.mCookiesContainer             = this.mHttpEntityParser.cookiesMapify( this.mCookiesContainer, this.mConnectiom.request );
        if( connectiom.request instanceof MultipartHttpServletRequest ) {
            Map<String, MultipartFile> mf   = ((MultipartHttpServletRequest) connectiom.request).getFileMap();
            this.mFilesMapContainer = new LinkedHashMap<>();
            for ( Map.Entry<String, MultipartFile> kv : mf.entrySet() ) {
                com.pinecone.summer.multiparts.MultipartFile multipartFile = new SpringMultiFile(kv.getValue());
                this.mFilesMapContainer.put( kv.getKey(), multipartFile );
            }
            this.mCurrentMultipartRequest   = connectiom.request;
            this.mPOSTMapContainer          = this.mHttpEntityParser.siftPostFromParameterMap ( this.mCurrentMultipartRequest, false );
            this.mGlobalParameterContainer  = this.mHttpEntityParser.requestMapJsonify        ( this.mCurrentMultipartRequest,false );
            return this;
        }
        this.mMultipartFilesMaker.refresh();
        /* Notice: 2020-12-25
         * Java Servlet abandoned multipart post.
         * Pinecone be forced to redefined $_POST.
         * **/
        if( this.mMultipartFilesMaker.isMultipart() ){
            this.mMultipartFilesMaker.interceptMultipartFiles();
            this.mFilesMapContainer         = this.mMultipartFilesMaker.getCurrentFilesMap();
            this.mCurrentMultipartRequest   = this.mMultipartFilesMaker.getCurrentMultipartRequest();
            this.mPOSTMapContainer          = this.mHttpEntityParser.siftPostFromParameterMap ( this.mCurrentMultipartRequest, false );
            this.mGlobalParameterContainer  = this.mHttpEntityParser.requestMapJsonify        ( this.mCurrentMultipartRequest,false );
        }
        else {
            this.mPOSTMapContainer         = this.mHttpEntityParser.siftPostFromParameterMap ( this.mConnectiom.request, false );
            this.mGlobalParameterContainer = this.mHttpEntityParser.requestMapJsonify        ( this.mConnectiom.request,false );
        }
        return this;
    }
}