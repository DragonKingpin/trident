package com.sauron.saurye.system;

import com.pinecone.framework.util.io.Tracer;
import com.pinecone.framework.util.Debug;
import com.sauron.saurye.system.legacy.Prototype;
import com.pinecone.framework.util.StringUtils;
import com.pinecone.summer.http.HttpURLParser;
import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.*;
import com.pinecone.summer.multiparts.MultipartFile;
import com.pinecone.summer.http.HttpMethod;
import com.pinecone.summer.prototype.Pagesion;
import com.pinecone.summer.prototype.Wizard;
import com.pinecone.Pinecone;
import com.sauron.saurye.system.authority.UserCertifier;
import com.sauron.saurye.system.auxiliary.QuerySpell;
import com.sauron.saurye.system.infrastructure.JasperFrame;
import com.sauron.saurye.system.properties.Paginate;
import com.sauron.saurye.system.properties.Properties;
import com.sauron.saurye.system.prototype.JasperModifier;
import com.sauron.saurye.system.prototype.JasperTraitExpresser;
import com.sauron.saurye.elements.AlchemistMaster;
import com.sauron.saurye.peripheral.Equipment.EquipmentPeddler;
import com.sauron.saurye.peripheral.Equipment.UIEquipmentPeddler;
import com.sauron.saurye.peripheral.Skill.CoreCoach;
import com.sauron.saurye.system.legacy.SauronMySQLExecutor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

/**
 * Like Javascript it is prototype chain.
 * system Mirror Class. (All frequently-used method and function can be clone. )
 * Extending this class will allow you invoke such $_GET, $_GPC, $_POST anywhere like PHP.
 */

public abstract class PredatorArchWizardum extends PredatorArchWizard implements JasperTraitExpresser {
    protected enum Occupation {
        W_FREE, W_FORCE_FREE, W_ENCHANTER, W_JSP_MODIFIER
    }

    protected JasperFrame mJasperFrame                  =  null  ;
    protected EquipmentPeddler        mUIEquipmentPeddler           =  null  ;
    protected WizardGeniesInvoker     mWizardGeniesInvoker          =  null  ;
    protected boolean                 mbGlobalJSPDominant           =  false ;
    private UserCertifier mUserCertifier                =  null  ;
    private QuerySpell mQuerySpell                   =  null  ;



    /** Base **/
    @Override
    protected void appendDefaultPageData(){
        super.appendDefaultPageData();
        this.$_REQUEST().setAttribute( "Prototype", this );
    }

    public PredatorArchWizardum( ArchConnection session ) {
        super( session );
        if( this instanceof Pagesion ){
            this.mbGlobalJSPDominant = this.isDefaultJSPModifier();
        }
        this.mJasperFrame          = new JasperFrame( this );
        this.mUIEquipmentPeddler   = new UIEquipmentPeddler( this );
        this.mWizardGeniesInvoker  = new WizardGeniesInvoker( this, this.system() );
        this.mUserCertifier        = new UserCertifier( this );
    }

    public PredatorArchWizardum() {
        super();
    }


    @Override
    public void afterSessionArrived( ArchConnection session ){
        super.afterSessionArrived( session );
        if( this instanceof Pagesion ){
            this.mbGlobalJSPDominant = this.isDefaultJSPModifier();
        }
        this.mJasperFrame          = new JasperFrame( this );
        this.mUIEquipmentPeddler   = new UIEquipmentPeddler( this );
        this.mWizardGeniesInvoker  = new WizardGeniesInvoker( this, this.system() );
        this.mUserCertifier        = new UserCertifier( this );
    }

    public void summoning() throws ServletException, IOException {
        if( this.currentUser().privilegeQualified() != UserCertifier.DisqualifiedType.DT_MATCHED ){
            try {
                String szQueryString = this.$_REQUEST().getQueryString();
                String szReferHref   = StringUtils.isEmpty(szQueryString) ? "" : "&referHref=" + HttpURLParser.encode( "?" + szQueryString );
                this.redirect( this.querySpell().gotoLogin() + szReferHref );
                return;
            }
            catch ( Exception e ) {
                this.handleException( e );
            }
        }

        super.summoning();
    }



    @Override
    public PredatorDispatcher getSystemDispatcher() {
        return (PredatorDispatcher) this.mDispatcher;
    }

    public JSONObject $_GSC() {
        return this.getSystemDispatcher().$_GSC();
    }

    public JSONObject $_GET  ( boolean bSafe ) {
        return this.getSystemDispatcher().$_GET( bSafe );
    }

    public JSONObject $_POST ( boolean bSafe ) {
        return this.getSystemDispatcher().$_POST( bSafe );
    }

    public HttpMethod currentHttpMethod(){
        return this.getConnection().currentHttpMethod();
    }

    public Map<String, MultipartFile> $_FILES() {
        return this.getConnection().$_FILES();
    }

    public boolean isDebugMode() {
        return this.system().getServiceSystemConfig().optBoolean( "DebugMode" );
    }



    /** Template **/
    public JasperFrame getJasperFrame(){
        return this.mJasperFrame;
    }

    public EquipmentPeddler equipmentPeddler(){
        return this.mUIEquipmentPeddler;
    }

    public CoreCoach coach() {
        return this.system().coach();
    }

    public AlchemistMaster alchemist() {
        return this.system().alchemist();
    }

    public UserCertifier currentUser() {
        return this.mUserCertifier;
    }



    /** RDB & System Basic Getter **/
    protected SauronMySQLExecutor mysql(){
        return this.system().mysql();
    }

    protected String tableName( String szShortTableName ) {
        return this.mysql().tableName( szShortTableName );
    }

    protected String assembleSimpleStackInfo ( StackTraceElement[] stackTraceElements, boolean bOnlySite ){
        StringBuilder s = new StringBuilder();
        String szSiteName = this.system().getSitesConfig().optString("name");
        for ( StackTraceElement ele: stackTraceElements ) {
            String szClassName = ele.getClassName();
            if( (!bOnlySite && szClassName.contains("com/pinecone")) || szClassName.contains( szSiteName ) ){
                s.append("   ").append(szClassName).append(".").append(ele.getMethodName()).append("(").append(ele.getFileName()).append(":").append(ele.getLineNumber()).append(") \n");
            }
        }
        return s.toString();
    }

    public    void handleSQLException( SQLException e ) {
        try {
            this.mDispatcher.traceSystem500Error(
                    String.format(
                            "<h3>Caught SQLException During Runtime: </h3>" +
                            "<h3>What: %s</h3>"+
                            "<p>Contact: %s</p>",
                            this.isDebugMode() ? e.getMessage() : "Query Compromised.", Pinecone.CONTACT_INFO
                    )
            );
            System.err.println(
                    String.format(
                            "****************** SQLException Caught ******************\n" +
                                    "ERROR SQL: %s\n" +
                                    "     What: %s\n" +
                                    "SQL State: (%s , %d)\n"+
                                    "       At: %s\n" +
                            "*********************************************************\n",
                            this.mysql().getLastSQLSentence(),
                            e.getMessage(),
                            e.getSQLState(), e.getErrorCode(),
                            this.assembleSimpleStackInfo( e.getStackTrace(), false )
                    )
            );
        }
        catch (ServletException|IOException e1){
            e1.printStackTrace();
        }
    }

    public    void handleException( Exception e ) {
        if( e instanceof TerminateSessionException ){
            throw (TerminateSessionException) e;
        }
        else if( e instanceof SQLException ){
            this.handleSQLException( (SQLException)e );
            return;
        }

        try{
            this.mDispatcher.traceSystem500Error( Prototype.prototypeName(e) + ":" + e.getMessage() );
            e.printStackTrace();
        }
        catch (ServletException|IOException e1){
            e1.printStackTrace();
        }
    }

    public    void rethrowStopSignal( Exception e ) {
        if( e instanceof TerminateSessionException ){
            throw (TerminateSessionException) e;
        }
        else if( e.getCause() instanceof TerminateSessionException ){
            throw (TerminateSessionException) e.getCause();
        }
        throw new IllegalStateException( "Reinterpret throw new instance to terminate it in an unusual way.", e );
    }

    public Properties properties() {
        return this.system().properties();
    }

    public Paginate paginateProperty() {
        return this.properties().paginate();
    }


    public void appendDefaultAttribute(String key, Object value){
        this.$_REQUEST().setAttribute( key,value );
    }



    /** Render **/
    public String fertilizedHF() throws ServletException, IOException {
        this.appendDefaultAttribute( "StaticHead", this.mJasperFrame.includeStaticHead( (Wizard) this ) );
        this.appendDefaultAttribute( "StaticFooter", this.mJasperFrame.includeFooter() );
        this.appendDefaultAttribute( "szPageData",this.mPageData.toString() );
        this.appendDefaultAttribute( "StaticPageEnd","</body></html>" );
        return this.jspTPLRender(this.prototypeName());
    }

    public String jspTPLRender() throws ServletException, IOException {
        return this.jspTPLRender(this.prototypeName());
    }

    public String jspTPLRender( String szJSPSimpleName ) throws ServletException, IOException {
        String szJSPFileName = ( (Wizard) this).getModularRole() + "/" + szJSPSimpleName + ".jsp";
        this.mDispatcher.jspTPLRenderPage( szJSPFileName );
        return szJSPFileName;
    }



    /** Redirect And Helper **/
    protected void smartRedirectWithModelParameter( String szWizardName, String szFunModelName ) throws IOException {
        String szParameter = this.system().getModelParameter() + "=" + szFunModelName;
        this.smartRedirect( szWizardName, szParameter );
    }

    protected void smartRedirect( String szWizardName, String szParameter ) throws IOException {
        String szRealURL = "?" + this.system().getWizardParameter() + "=" + szWizardName;
        if( szParameter != null && !szParameter.isEmpty() ){
            szRealURL += "&" + szParameter;
        }
        this.redirect( szRealURL );
    }

    protected void smartRedirect( String szWizardName ) throws IOException {
        this.smartRedirect( szWizardName, null );
    }

    public    void alert( String szTitle, int nState, Object url ) throws IOException {
        this.redirect(
                String.format( "?%s=%s&title=%s&state=%d&url=%s",
                        this.system().getWizardParameter(),
                        this.system().getServiceSystemConfig().getString("AlertPage"),
                        URLEncoder.encode( szTitle, this.system().getServerCharset() ), nState,
                        URLEncoder.encode( url.toString(), this.system().getServerCharset() )
                )
        );
        this.stop();
    }

    public    void checkResult( boolean bResult, String szTitle, Object url ) throws IOException {
        JSONObject proto = this.getWizardProto(this.system().getServiceSystemConfig().getString("AlertPage"));

        if( bResult ){
            if( szTitle == null ) {
                szTitle = proto.optString("defaultSuccessTitle");
            }
            this.alert( szTitle, 1 , url );
        }
        else {
            if( szTitle == null ) {
                szTitle = proto.optString("defaultFailTitle");
            }
            this.alert( szTitle, 0, url );
        }
    }

    public    void checkResult( boolean bResult, String szTitle ) throws IOException {
        this.checkResult( bResult, szTitle, -1 );
    }

    public    void checkResult( boolean bResult ) throws IOException {
        this.checkResult( bResult, null, -1 );
    }

    protected String assertString( String s ) throws IOException, ServletException {
        if( StringUtils.isEmpty( s )  ){
            this.getSystemDispatcher().traceSystem500Error(
                    "<h3>Wizard was summoned with unexpected state.</h3>" +
                            "<h3>Please contact administrator[" + Pinecone.CONTACT_INFO + "] for more information.</h3>"
            );
            this.stop();
        }
        return s;
    }

    protected JSONArray assertSelectResult( JSONArray object ) throws IOException, ServletException {
        if( object == null || object.isEmpty() ){
            this.getSystemDispatcher().traceSystem500Error(
                    "<h3>Wizard was summoned with unexpected state.</h3>" +
                            "<h3>Please contact administrator[" + Pinecone.CONTACT_INFO + "] for more information.</h3>"
            );
            this.stop();
        }
        return object;
    }



    /** QuerySpell **/
    public String spawnWizardActionSpell ( String szPrototype, String szActionFnName ){
        String szQueryString = "?" + this.system().getWizardParameter() + "=" + szPrototype;
        if( szActionFnName != null && !szActionFnName.isEmpty() ){
            return szQueryString + "&" + this.system().getModelParameter() + "=" + szActionFnName;
        }
        return szQueryString;
    }

    public String spawnWizardControlSpell ( String szPrototype, String szControlFnName ){
        String szQueryString = "?" + this.system().getWizardParameter() + "=" + szPrototype;
        if( szControlFnName != null && !szControlFnName.isEmpty() ){
            return szQueryString + "&" + this.system().getControlParameter() + "=" + szControlFnName;
        }
        return szQueryString;
    }

    public String spawnActionQuerySpell(){
        return this.spawnActionQuerySpell( null );
    }

    public String spawnControlQuerySpell(){
        return this.spawnControlQuerySpell( null );
    }

    public QuerySpell querySpell(){
        if( this.mQuerySpell == null ){
            this.mQuerySpell = new QuerySpell( this );
        }
        return this.mQuerySpell;
    }



    /** JasperTraitExpresser **/
    public Object summonNormalGenieByCallHisName( String szGenieName ) throws NaughtyGenieInvokedException {
        return this.mWizardGeniesInvoker.invokeNormalGenieByCallHisName( szGenieName );
    }

    public void setPhenotypicTrait( boolean bDominant ) {
        this.mbGlobalJSPDominant = bDominant;
    }

    public boolean isJasperDominant() {
        return this.mbGlobalJSPDominant;
    }

    public boolean isDefaultJSPModifier() {
        Annotation[] annotations = this.getClass().getAnnotations();
        for( Annotation annotation : annotations ){
            if( annotation instanceof JasperModifier){
                return ((JasperModifier) annotation).value();
            }
        }
        return false;
    }



    /** Trace **/
    protected Tracer console = Debug.console();

    public Tracer trace( Object data, Object ...more ) {
        return Debug.trace( data, more );
    }
}
