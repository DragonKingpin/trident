package com.sauron.saurye.peripheral.Skill.MVC;

import com.pinecone.framework.util.StringUtils;
import com.pinecone.framework.util.json.JSONObject;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.peripheral.Skill.Util.PaginateHelper;
import com.sauron.saurye.system.PredatorArchWizardum;
import com.sauron.saurye.peripheral.Skill.Coach;
import com.sauron.saurye.peripheral.Skill.SkillSoul;
import com.sauron.saurye.peripheral.Skill.BasicSkill;

import java.io.IOException;

public class ViewLayerSkill extends SkillSoul implements BasicSkill {
    public ViewLayerSkill(Coach coach ){
        super( coach );
    }

    @Override
    public String prototypeName() {
        return this.getClass().getSimpleName();
    }


    private void paginationPreTreat        (int nPageLimit, PredatorArchWizardum wizardProto, String szCountSQL, String szConditionSQL, String szCountFieldSQL ) {
        JSONObject pageData         = wizardProto.getPageData();
        JSONObject safeControlMap   = wizardProto.$_GSC();

        pageData.put ( wizardProto.paginateProperty().getVarPageLimit()   , nPageLimit );
        pageData.put ( wizardProto.paginateProperty().getVarPageDataSum() , this.mysql().countFromTable( String.format( szCountSQL, szCountFieldSQL, szConditionSQL ) ) );
        pageData.put ( wizardProto.paginateProperty().getVarBeginNum()    , PaginateHelper.getPageBeginDefault (
                safeControlMap.opt( wizardProto.paginateProperty().getQueryPageID() ),
                pageData.optInt( wizardProto.paginateProperty().getVarPageDataSum() ),
                nPageLimit )
        );
    }

    public void simplePaginationPreTreat    (Pagesion that, String szCountSQL, String szConditionSQL ) {
        this.simplePaginationPreTreat( that, szCountSQL, szConditionSQL, "COUNT(*)" );
    }

    public void simplePaginationPreTreat    (Pagesion that, String szCountSQL, String szConditionSQL, String szCountFieldSQL ) {
        PredatorArchWizardum wizardProto = (PredatorArchWizardum)that;

        this.paginationPreTreat(
                wizardProto.getModularConfig().optInt( wizardProto.paginateProperty().getWConfPageLimit() ),
                wizardProto, szCountSQL, szConditionSQL, szCountFieldSQL
        );
    }

    public int adjustablePaginationPreTreat (Pagesion that, String szCountSQL, String szConditionSQL, String szCountFieldSQL, int nPageLimit ) {
        PredatorArchWizardum wizardProto = (PredatorArchWizardum)that;
        JSONObject queryStringMap      = wizardProto.$_GET();

        if( queryStringMap.hasOwnProperty( wizardProto.paginateProperty().getQueryPageLimit() ) ) {
            int nPL = queryStringMap.optInt( wizardProto.paginateProperty().getQueryPageLimit() );
            if( nPL > 0 ){
                nPageLimit = nPL;
            }
        }

        this.paginationPreTreat ( nPageLimit , wizardProto, szCountSQL, szConditionSQL, szCountFieldSQL );
        return nPageLimit;
    }

    public int adjustablePaginationPreTreat (Pagesion that, String szCountSQL, String szConditionSQL, String szCountFieldSQL ) {
        PredatorArchWizardum wizardProto = (PredatorArchWizardum)that;
        return this.adjustablePaginationPreTreat(
                that, szCountSQL, szConditionSQL, szCountFieldSQL,
                wizardProto.getModularConfig().optInt( wizardProto.paginateProperty().getWConfPageLimit() )
        );
    }

    public int adjustablePaginationPreTreat (Pagesion that, String szCountSQL, String szConditionSQL ) {
        return this.adjustablePaginationPreTreat( that, szCountSQL, szConditionSQL, "COUNT(*)" );
    }

    public void assertQueryAction           (Pagesion that, String szQuery, String szActionWith ) throws IOException {
        if( StringUtils.isEmpty( szQuery ) ){
            PredatorArchWizardum self = (PredatorArchWizardum) that;
            self.redirect( self.spawnActionQuerySpell() + szActionWith );
            self.stop();
        }
    }

}
