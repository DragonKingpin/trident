package com.pinecone.summer;

import com.pinecone.framework.unit.LinkedMultiValueMap;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.multiparts.MultipartFile;
import com.pinecone.summer.multiparts.commons.CommonsMultipartFiles;
import com.pinecone.summer.http.HttpEntityParser;
import com.pinecone.summer.http.HttpMethod;
import com.pinecone.summer.prototype.Connectson;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public abstract class ArchConnection implements Connectson {
    protected ArchConnectDispatcher mDispatcher;
    protected ArchServiceSystem        mServiceSystem;

    protected Connectiom          mConnectiom;
    protected HttpServletRequest  mCurrentMultipartRequest;

    protected JSONObject mGlobalParameterContainer  = null; /** GPC  **/
    protected JSONObject mGETMapContainer           = null; /** GET  **/
    protected JSONObject mPOSTMapContainer          = null; /** POST **/

    protected HttpMethod                 mCurrentHttpMethod    = HttpMethod.GET;
    protected CommonsMultipartFiles      mMultipartFilesMaker  = null ;
    protected HttpEntityParser           mHttpEntityParser     = null ;
    protected Map<String, MultipartFile> mFilesMapContainer    = new LinkedMultiValueMap() ;
    protected Map<String, Cookie>        mCookiesContainer     = new TreeMap<>();


    public ArchConnection( ArchConnectDispatcher dispatcher ) {
        this.mDispatcher       = dispatcher;
        this.mServiceSystem       = this.mDispatcher.getServiceSystem();
        this.mHttpEntityParser = this.mDispatcher.getHttpEntityParser();
    }

    protected ArchConnection(ArchConnectDispatcher dispatcher, Connectiom connectiom ) {
        this( dispatcher );
        this.apply(connectiom);
    }

    protected ArchConnection apply( Connectiom connectiom ) {
        this.mConnectiom = connectiom;
        this.mMultipartFilesMaker = new CommonsMultipartFiles( this );
        this.mConnectiom.afterConnectionRipe( this );
        return this;
    }


    @Override
    public ArchConnectDispatcher getDispatcher(){
        return this.mDispatcher;
    }

    @Override
    public ArchServiceSystem getServiceSystem() {
        return this.mServiceSystem;
    }


    @Override
    public HttpServletRequest getRequest() {
        return this.mConnectiom.request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return this.mConnectiom.response;
    }

    @Override
    public HttpServlet getServlet() {
        return this.mConnectiom.servlet;
    }

    @Override
    public HttpServletRequest getMultipartRequest() {
        return this.mCurrentMultipartRequest;
    }

    @Override
    public boolean isMultipartRequest() {
        return this.mMultipartFilesMaker.isMultipart();
    }

    @Override
    public JSONObject $_GPC(){
        return this.mGlobalParameterContainer;
    }

    @Override
    public JSONObject $_GET(){
        return this.mGETMapContainer;
    }

    @Override
    public JSONObject $_POST(){
        return this.mPOSTMapContainer;
    }

    @Override
    public PrintWriter writer() throws IOException {
        return this.getResponse().getWriter();
    }

    @Override
    public ServletOutputStream out() throws IOException {
        return this.getResponse().getOutputStream();
    }

    @Override
    public HttpServletRequest $_REQUEST(){
        return this.$_REQUEST( false );
    }

    @Override
    public HttpServletRequest $_REQUEST ( boolean bUsingMultipart ){
        if ( bUsingMultipart && this.isMultipartRequest() ){
            return this.mCurrentMultipartRequest;
        }
        return this.mConnectiom.request;
    }

    @Override
    public HttpServletResponse $_RESPONSE(){
        return this.mConnectiom.response;
    }

    @Override
    public Map<String, MultipartFile> $_FILES() {
        return this.mFilesMapContainer;
    }

    @Override
    public Map<String, Cookie > $_COOKIE() {
        return this.mCookiesContainer;
    }


    @Override
    public HttpMethod currentHttpMethod(){
        return this.mCurrentHttpMethod;
    }


    public CommonsMultipartFiles getMultipartFilesMaker() {
        return this.mMultipartFilesMaker;
    }

    public Connectiom getConnectiom() {
        return this.mConnectiom;
    }


    class SpringMultiFile implements MultipartFile {
        org.springframework.web.multipart.MultipartFile multipartFile;
        public SpringMultiFile( org.springframework.web.multipart.MultipartFile multipartFile ) {
            this.multipartFile = multipartFile;
        }

        @Override
        public String getName() {
            return this.multipartFile.getName();
        }

        @Override
        public String getOriginalFilename() {
            return this.multipartFile.getOriginalFilename();
        }

        @Override
        public String getContentType() {
            return this.multipartFile.getContentType();
        }

        @Override
        public boolean isEmpty() {
            return this.multipartFile.isEmpty();
        }

        @Override
        public long getSize() {
            return this.multipartFile.getSize();
        }

        @Override
        public byte[] getBytes() throws IOException {
            return this.multipartFile.getBytes();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return this.multipartFile.getInputStream();
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            this.multipartFile.transferTo(dest);
        }
    }
}
