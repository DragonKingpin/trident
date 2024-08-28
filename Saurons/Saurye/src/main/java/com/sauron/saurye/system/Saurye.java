package com.sauron.saurye.system;

import com.pinecone.framework.util.json.*;
import com.pinecone.summer.*;
import com.pinecone.framework.util.mysql.MySQLHost;
import com.sauron.saurye.system.infrastructure.JasperMenu;
import com.sauron.saurye.system.properties.Properties;
import com.sauron.saurye.elements.AlchemistMaster;
import com.sauron.saurye.peripheral.Skill.CoreCoach;
import com.sauron.saurye.peripheral.Skill.Util.TextBasicProcessor;
import com.sauron.saurye.system.legacy.SauronMySQLExecutor;

import javax.servlet.*;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Saurye extends ArchServiceSystem {
    private JSONObject           mSitesConfig            = null;
    private String               mHostConfigPath         = null;
    private String               mszWizardPackageName          ;
    private String               mszSystemDispatcher           ;
    private boolean              mbStaticRunMode         = false;
    private JasperMenu mMenuSpawner            = null;
    protected JSONObject         mRDBConfig              = null;
    protected JSONObject         mServiceSystemConfig       = null;
    private SauronMySQLExecutor  mysql                   = null;
    private com.sauron.saurye.system.properties.Properties mGlobalProperties       = null;
    private AlchemistMaster      mAlchemistMaster        = null;
    private CoreCoach            mCoreCoach              = null;
    private TextBasicProcessor   mTextBasicProcessor     = null;

    public Saurye( String szResourcesPath, String szConfigFileName ) throws IOException {
        this( szResourcesPath, szConfigFileName, false );
    }

    public Saurye( String szResourcesPath, String szConfigFileName, boolean bStaticRunMode ) throws IOException {
        super( szResourcesPath, szConfigFileName );
        this.setStaticRunMode( bStaticRunMode );
    }

    public Saurye( SystemServlet servlet ) throws IOException {
        super( servlet );
    }

    public void setLegacyServlet( ServletContext context, String szClassPath ){
        this.mServletContext = context;
        this.mSystemServlet  = new SystemServlet(){
            @Override
            public String getClassPath() {
                return szClassPath + "/Saurons/Saurye/web/";
            }

            @Override
            public ServletContext getServletContext() {
                return Saurye.this.mServletContext;
            }
        };

        this.registerRootClassPath( this.mSystemServlet.getClassPath() );
    }


    public SauronMySQLExecutor mysql() {
        return this.mysql;
    }

    private void              initMySQL(){
        this.mRDBConfig           = this.getServiceSystemConfig().getJSONObject("RDB");
        try{
            this.mysql = new SauronMySQLExecutor(
                    new MySQLHost (
                            this.getRDBConfig().getString("Location"),
                            this.getRDBConfig().getString("Username"),
                            this.getRDBConfig().getString("Password")
                    )
            );
        }
        catch ( SQLException e ){
            e.printStackTrace();
            System.err.println("Failed to initialize MySQL executor: " + e.getMessage());
        }

        this.mysql.setTableNamespace( this.getRDBConfig().getString("TableNamespace") );
    }

    public JasperMenu         menu(){ return this.mMenuSpawner; }

    public com.sauron.saurye.system.properties.Properties properties() {
        return this.mGlobalProperties;
    }

    public CoreCoach          coach() {
        return this.mCoreCoach;
    }

    public AlchemistMaster    alchemist() {
        return this.mAlchemistMaster;
    }

    public TextBasicProcessor textBasicProcessor() {
        return this.mTextBasicProcessor;
    }



    public String getMainMenuConfig(){
        return this.getServiceSystemConfig().getString("MainMenu");
    }

    public String getFullMenuPath(){
        return this.getResourcesPath() + this.getMainMenuConfig();
    }

    public String getHostConfigPath() {
        if( this.mHostConfigPath == null ){
            this.mHostConfigPath = this.getResourcesPath() + this.getHosts().getString( this.prototypeName() );
        }
        return this.mHostConfigPath;
    }

    public String getWizardPackageName(){
        return this.mszWizardPackageName;
    }

    public String get16BitInnerPassword() {
        return this.getServiceSystemConfig().getString("16BitInnerPassword");
    }

    public JSONObject getSitesConfig() {
        return this.mSitesConfig;
    }

    public JSONObject getServiceSystemConfig(){
        if( this.mServiceSystemConfig == null ){
            this.mServiceSystemConfig = this.mSitesConfig.getJSONObject( "System" );
        }
        return this.mServiceSystemConfig;
    }

    public JSONObject getWizardsConfig(){
        return this.mSitesConfig.getJSONObject( "WizardsList" );
    }

    public JSONObject getPredatorUploadConfig(){
        return this.getServiceSystemConfig().getJSONObject( "UploadConfig" );
    }

    public JSONObject getGlobalPropertiesConfig() {
        return this.mServiceSystemConfig.getJSONObject( "GlobalProperties" );
    }

    public JSONObject getPeripheralConfig() {
        return this.mSitesConfig.getJSONObject( "peripheral" );
    }

    public JSONObject getRDBConfig(){
        return this.mRDBConfig;
    }

    public JSONObject getWizardProto( String prototypeName ) {
        return this.getWizardsConfig().getJSONObject( prototypeName );
    }

    public String     getSystemDispatcherConfig(){
        return this.mszSystemDispatcher;
    }

    @Override
    public PredatorDispatcher handleByDispatcher( RouterType routerType ) {
        ArchConnectDispatcher dispatcher = null;
        if ( !this.mbStaticRunMode && this.mszSystemDispatcher != null && !this.mszSystemDispatcher.isEmpty() ){
            dispatcher = SystemSpawner.spawnDispatcher( this.mszSystemDispatcher, this, routerType  );
        }
        return (PredatorDispatcher) dispatcher;
    }

    public void setStaticRunMode ( boolean bState ){
        this.mbStaticRunMode = bState;
    }


    @Override
    public String getWizardSummonerConfig() { return this.getServiceSystemConfig().getString("WizardSummoner"); }

    @Override
    public void init() throws ServletException {
        long nStartTime = System.currentTimeMillis();

        try {
            this.mSitesConfig         = new JSONMaptron( this.readFileContentAll( this.getHostConfigPath() ) );
            this.mszWizardPackageName = this.getServiceSystemConfig().getString( "WizardPackageName");
            this.mszSystemDispatcher  = this.getServiceSystemConfig().getString( "SystemDispatcher" );
            this.initMySQL();
            this.mGlobalProperties   = new Properties( this );
            this.mAlchemistMaster    = new AlchemistMaster( this );
            this.mCoreCoach          = new CoreCoach( this );
            this.mTextBasicProcessor = new TextBasicProcessor( this );
        }
        catch ( IOException | JSONException e ){
            e.printStackTrace();
        }

        this.mMenuSpawner = new JasperMenu( this );
        this.mMenuSpawner.loadMenu();

        System.out.println( "----------------------------------------------" );
        System.out.println( "Bean Nuts Hazelnut Sauron Eyes Initiated" );
        System.out.println( "Time: " + ( new SimpleDateFormat("yyyy-MM-dd HH：mm：ss") ).format(new Date()) );
        System.out.println( String.format( "Done: %d /ms !", System.currentTimeMillis() - nStartTime ) );
        System.out.println( "----------------------------------------------" );
    }

    public String prototypeName() {
        return this.getClass().getSimpleName();
    }


    static int threshold = 1;

    public static ArrayList<String > findEasyReplaceString( String target,String[] temp, boolean bContinuity ){
        ArrayList<String > res = new ArrayList<String >();
        int n1 = target.length();
        int n2 = temp.length;
        for(int i=0;i<n2;i++){
            int nDifferent = temp[i].length() - n1;
            if(nDifferent!=0){
                continue;
            }
            String szTemp = temp[i].toLowerCase();
            int nMistake = 0;
            boolean isBroken = false;
            int nContinuity=0;
            for(int j=0;j<szTemp.length();j++){
                if(szTemp.charAt(j)!=target.charAt(j)){
                    nMistake++;
                    if(!isBroken){
                        nContinuity++;
                        isBroken = true;
                    }
                }
                else{
                    isBroken = false;
                }
            }
            if(nMistake==threshold){
                if(bContinuity&&nContinuity<2){
                    res.add(temp[i]);
                }
                if(!bContinuity&&nContinuity>=2){
                    res.add(temp[i]);
                }
            }
        }
        return res;
    }

    public static ArrayList<String > findInsertString( String target,String[] temp,boolean bContinuity ){
        ArrayList<String > res = new ArrayList<String >();
        int n1 = target.length();
        int n2 = temp.length;
        for(int i=0;i<n2;i++){
            int nDifferent = Math.abs(temp[i].length() - n1);
            int index = 0;
            if(nDifferent!=threshold){
                continue;
            }
            String szTemp = temp[i].toLowerCase();
            int Length = Math.max(szTemp.length(),n1);
            int smallLength = Math.min(szTemp.length(),n1);
            String szLong = szTemp.length()>n1?szTemp:target;
            String szShort = szTemp.length()<n1?szTemp:target;
            int nContinuity = -1;
            boolean isBroken = false;
            for(int j=0;j<Length;j++){
                if(index<smallLength&&szLong.charAt(j)==szShort.charAt(index)){
                    index++;
                }
                else{
                    if(nContinuity!=-1&&j-nContinuity>1){
                        isBroken = true;
                    }
                    nContinuity = j;
                }
            }
            if( index == smallLength ){
                if( ( bContinuity || threshold == 1 ) && !isBroken ){
                    res.add(temp[i]);
                }
                if( !bContinuity && isBroken ){
                    res.add(temp[i]);
                }
            }
        }
        return res;
    }

    public static ArrayList<String > findReplaceString( String target,String[] temp ){
        ArrayList<String > res = new ArrayList<String >();
        int n1 = target.length();

        int n2 = temp.length;
        for( int i = 0; i < n2; i++ ){
            int nDifferent = temp[i].length() - n1;
            if( nDifferent > 0 || nDifferent < -2 ){
                continue;
            }
            String szTemp = temp[i];
            int sum = 0;
            int index = 0;
            int j =0;
            int nMistake = n1 - temp[i].length();
            while( index < szTemp.length() ){
                if( target.charAt(j) == szTemp.charAt(index) ){
                    index++;
                }
                else {
                    if(nMistake!=0){
                        nMistake--;
                    }
                    else{
                        index++;
                    }
                    sum++;
                }
                j++;
            }
            if( sum <= 0 ){
                res.add(szTemp);
            }
        }
        return res;
    }






    public static String wordify( String sz ) {
        char [] buf = sz.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for ( int i = 0; i < buf.length; i++ ) {
            char c = buf[i];

            if( Character.isLetter( c ) ) {
                stringBuilder.append( c );
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();//Nop
        Saurye predator = new Saurye( "E:/MyFiles/CodeScript/Project/Hazelnut/Sauron/Godview/system/setup/saurye/", "config.json5", true );
        predator.init();








/*        FileReader fileReader = new FileReader( "M:/slang_defines.json5" );

        Scanner scanner = new Scanner( fileReader );

        String szNextLine = "";

        try {
            while ( ( szNextLine = scanner.nextLine() ) != null ) {

            }
        }
        catch ( NoSuchElementException e ) {
            scanner.close();
        }


        Debug.echo( szNextLine );*/





        //Thread.sleep( 100000 );



        //Debug.trace( predator.mysql().fetch( "SELECT * FROM " + predator.mysql().tableName("test") ) );

        //Debug.trace( predator.alchemist().mutual().word().historyTeller().apply( "fuck", 1858 ).getHistoryRateLog() );

//        LSMEvaluator lsmEvaluator = predator.alchemist().mutual().word().historyTeller().apply( "niche", 1900 ).lsmEvaluator(100,9);

//        Debug.trace(
//                predator.alchemist().mutual().word().wordWeightTree().apply( "open" ).get_form_weights()
//        );



        //predator.alchemist().mutual().sentence().logicTree().apply

//        Debug.trace( predator.alchemist().mutual().sentence().logicTree().apply(
//                "For some time past vessels had been met by \"an enormous thing,\" a long object, spindle-shaped, occasionally phosphorescent," +
//                        " and infinitely larger and more rapid in its movements than a whale." ).keyWordify( false )
//        );




//        String target = "type";
//        String temp[] = {"typeee","rtypee","typeye","tp","PYte","tpey","tpr","tpye","rype","tpe","rpe","ppe","ryre","tper","rrtp","ty","te","typee","tyype","tye","ype"};
//        Object res[] = findEasyReplaceString(target,temp);
//        for(int i=0;i<res.length;i++){
//            System.out.println(res[i]);
//        }






//        TreeSet<String> wordsSet = new TreeSet<>();
//
//        for ( int i = 1; i < 23; i++ ) {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("J:/AC/SRT/SRT ("+i+").srt")));
//
//            String szLine = null;
//            while ( ( szLine = bufferedReader.readLine() ) != null ) {
//                if( szLine.startsWith( "{\\fs13}" ) ) {
//                    String[] debris = szLine.split("\\{\\\\fs13\\}");
//                    if( debris.length > 1 ) {
//                        szLine = debris[1];
//                    }
//
//                    debris = szLine.split(" ");
//
//                    for ( String word : debris ) {
//                        String szReal = wordify( word );
//                        if( !szReal.isEmpty() ) {
//                            wordsSet.add( szReal.toLowerCase() );
//                        }
//                    }
//                }
//            }
//
//        }
//
//
//        TreeSet<String> spSet = new TreeSet<>();
//        for ( String szWord : wordsSet ) {
//            JSONArray jSQLLevels = predator.mysql().fetch( "SELECT w_level_cache FROM predator_band_cache WHERE en_word = '" + szWord+ "'" );
//
//            try {
//                String jszLevels = jSQLLevels.optJSONObject(0).optString("w_level_cache" );
//                if( jszLevels.indexOf( "TEM8" ) > 0 ) {
//                    JSONArray jLevels = new JSONArray( jszLevels );
//                    spSet.add( szWord );
//                    Debug.trace( szWord, jLevels );
//                }
//            }
//            catch ( NullPointerException e ) {
//
//            }
//        }
//
//
//        Debug.trace( wordsSet.size(), spSet.size() );












//        JSONArray jWords = new JSONArray( FileUtils.readAll( "J:/MutualWordsLibList.json5" ) );
//        String[] pWords  = new String[ jWords.length() ];
//        for ( int i = 0; i < jWords.length(); i++ ) {
//            pWords[ i ] = jWords.getString( i );
//        }
//
//        //FileWriter sqls  = new FileWriter( new File( "J:/zzz.sql" ) ) ;
//
//        ArrayList<String > mutants = findReplaceString( "item", pWords );
//        Debug.trace( "system", mutants );

//        for ( int i = 0; i < jWords.length(); i++ ) {
//            String szWord = jWords.getString( i );
//
//            if ( szWord.length() > 3 ){
//                //ArrayList<String > mutants = findEasyReplaceString( szWord, pWords, false );
//                ArrayList<String > mutants = findReplaceString( szWord, pWords );
//                Debug.trace( szWord, mutants );
////                for( String szIsomer : mutants ){
////                    sqls.write( String.format(
////                            "INSERT INTO predator_words_mutant (en_word, en_mutant, mut_exponent, mut_type, mut_length) VALUES ( '%s', '%s', '3x', 'HeterPointReplace',%d ); \n" ,
////                            szWord, szIsomer, szWord.length()
////                    ) );
////                }
//            }
//
//            Debug.trace( i );
//
//        }
        //sqls.close();
















//        String[] levels = new String[]{ "NEMT", "CET4", "CET6", "TEM4", "TEM8", "GRE" };
//        TreeSet<String> onlyWord = new TreeSet<>();
//        for ( String level : levels ) {
//            JSONArray jWords = predator.mysql().fetch( "SELECT en_word, g_name FROM predator_mutual_glossary_words WHERE g_name = '" + level + "'" );
//
//            for ( Object obj : jWords ) {
//                JSONObject row = ( JSONObject ) obj;
//
//                onlyWord.add( row.optString( "en_word" ) );
//            }
//        }
//

//        TreeSet<String> greOnly = new TreeSet<>();
//        JSONArray jWords = predator.mysql().fetch( "SELECT en_word, g_name FROM predator_mutual_glossary_words WHERE g_name = 'GRE'" );
//
//        for ( Object obj : jWords ) {
//            JSONObject row = ( JSONObject ) obj;
//            String szWord  = row.optString( "en_word" );
//            if( !onlyWord.contains( szWord ) ){
//                greOnly.add( szWord );
//            }
//        }

//        TreeSet<String> greOnly = new TreeSet<>();
//        JSONArray jWords = predator.mysql().fetch( "SELECT `en_word` FROM predator_w_weight_t_union_base WHERE w_freq_base < 10000" );
//
//        for ( Object obj : jWords ) {
//            JSONObject row = ( JSONObject ) obj;
//            String szWord  = row.optString( "en_word" );
//            if( !onlyWord.contains( szWord ) ){
//                greOnly.add( szWord );
//            }
//        }
//
//        Debug.trace( greOnly, greOnly.size() );








//        JSONArray sentences = predator.mysql().fetch( "SELECT * FROM predator_ln_chapters_substances WHERE s_novel = 'Great Expectations'" );
//
//
//        JSONObject wordsSet = new JSONMaptron();
//
//        for ( Object obj : sentences ) {
//            JSONObject row = (JSONObject) obj;
//
//            String szSentence = StringUtils.hypertext2Text( row.optString( "s_content" ), false, false ) ;
//
//            String[] debris = predator.textBasicProcessor().en_segmenting_array( szSentence );
//
//            for ( String szWord : debris ) {
//                if( Pattern.compile("[`~!@#$^&*()=|{}':;',\\\\[\\\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ]").matcher( szWord ).find() ){
//                    //Debug.trace( szWord );
//                    continue;
//                }
//                JSONArray protos = predator.mysql().fetch(
//                        "SELECT `w_prototype` FROM predator_mutual_inflections WHERE " +
//                        "i_type != 'Self' AND w_inflection = '" + StringUtils.addSlashes( szWord ) + "'"
//                );
//                String szPrototype = szWord;
//                if( !protos.isEmpty() ){
//                    szPrototype = protos.optJSONObject( 0 ).optString( "w_prototype" );
//                    Debug.echo( szWord + " " + szPrototype ,"\n" );
//                }
//
//                JSONArray band = predator.mysql().fetch(
//                        "SELECT `w_level_cache` FROM predator_band_cache WHERE " +
//                                "en_word = '" + StringUtils.addSlashes( szPrototype ) + "'"
//                );
//
//                JSONObject wordInfo = new JSONMaptron();
//                wordInfo.put( "word", szPrototype );
//                if( !band.isEmpty() ){
//                    wordInfo.put( "band", band.optJSONObject( 0 ).optString( "w_level_cache" ) );
//                }
//                else {
//                    wordInfo.put( "band", "[]" );
//                }
//
//                JSONArray freqInfo = predator.mysql().fetch(
//                        "SELECT `w_freq_base` FROM predator_w_weight_t_union_base WHERE " +
//                                "en_word = '" + StringUtils.addSlashes( szPrototype ) + "'"
//                );
//
//                if( !freqInfo.isEmpty() ){
//                    wordInfo.put( "freq", freqInfo.optJSONObject( 0 ).optString( "w_freq_base" ) );
//                }
//                else {
//                    wordInfo.put( "freq", "228311" );
//                }
//
//                wordInfo.put( "sentence", szSentence );
//                wordsSet.put( szPrototype.toLowerCase(), wordInfo );
//
//            }
//        }
//
//
//
//        FileWriter fileWriter = new FileWriter( "J:/buff.json" );
//
//        JSONArray arraified   = wordsSet.toJSONArray();
//        JSONObject sqlifiable = new JSONMaptron();
//        sqlifiable.put( "ROWS", arraified );
//
//        sqlifiable.write( fileWriter, 4 );
//        fileWriter.close();
//
//        Debug.trace( wordsSet.size(), wordsSet );



//        JSONArray jWords = new JSONArray( FileUtils.readAll( "J:/MutualWordsLibList.json5" ) );
//
//        TreeMap<Double, JSONArray > similarMap = new TreeMap<>();
//
//        for ( Object obj : jWords ) {
//            double nSimi = MathHelper.getCosineSimilarity( "tacitum", (String) obj );
//
//            if ( similarMap.containsKey( nSimi ) ) {
//                similarMap.get( nSimi ).put( obj );
//            }
//            else {
//                JSONArray dummy = new JSONArraytron();
//                dummy.put( obj );
//                similarMap.put( nSimi, dummy );
//            }
//
//        }
//
//
//        Debug.trace( similarMap );









        System.out.println("\nRuntime : " + (System.currentTimeMillis() - startTime) + "ms");
    }


    //Tables

    public static final String TABLE_CN_STOP_WORD          = "sys_cn_stopword";
    public static final String TABLE_EN_STOP_WORD          = "sys_en_stopword";
    public static final String TABLE_NEWS_PAGE             = "news_page";
    public static final String TABLE_INDEX_ASSETS          = "index_assets";
    public static final String TABLE_MUTUAL_GLOSSARY       = "mutual_glossary";

}
