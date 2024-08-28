package com.sauron.saurye.wizard.System.PredatorAlertPage;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.system.prototype.JasperModifier;

@JasperModifier
public class PredatorAlertPageModel extends PredatorAlertPage implements Pagesion {
    public PredatorAlertPageModel( ArchConnection connection  ){
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();

        if( $_GSC().optString("title") == null || $_GSC().optString("title").isEmpty() ){
            this.mDispatcher.traceSystem500Error( "You have no permission to access this file ! <p style='margin-bottom: 2%;'>Access Denied !</p>" );
            this.stop();
        }

        this.mPageData.put( "title", "操作成功" );
        this.mPageData.put( "url", -1 );
        this.mPageData.put( "state", -1 );
    }

}
