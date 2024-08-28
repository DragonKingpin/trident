package com.sauron.saurye.wizard.User.NovelGrimoire;

import com.pinecone.framework.util.StringUtils;
import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.peripheral.Skill.Util.PaginateHelper;
import com.sauron.saurye.system.prototype.JasperModifier;

import java.sql.SQLException;
import java.util.regex.Pattern;


@JasperModifier
public class NovelGrimoireModel extends NovelGrimoire implements Pagesion {
    public NovelGrimoireModel( ArchConnection connection ) {
        super(connection);
    }

    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
        this.novelList();
    }

    public void novelList() throws SQLException {
        String szKeyWord = $_GSC().optString("keyWord");
        String szConditionSQL = "";

        if( !StringUtils.isEmpty( szKeyWord ) ){
            boolean bIsCnLike = Pattern.compile( "[\\u4E00-\\u9FA5]+" ).matcher( szKeyWord ).find();
            if( !bIsCnLike ){
                szConditionSQL += " WHERE `en_title` LIKE '%" + szKeyWord + "%' OR `n_author` LIKE '%" + szKeyWord + "%' ";
            }
            else {
                szConditionSQL += " WHERE `cn_title` LIKE '%" + szKeyWord + "%' ";
            }
        }

        int nPageLimit = this.coach().model().adjustablePaginationPreTreat(this,
                String.format( "SELECT %%s FROM %s AS tNovels %%s", this.alchemist().mutual().literary().tabNovelsNS() ), szConditionSQL, "COUNT(*)"
        );

        this.mPageData.put( "NovelList", this.alchemist().mutual().literary().getNovelInfo(
                szConditionSQL + PaginateHelper.formatLimitSentence (
                this.mPageData.optLong( this.paginateProperty().getVarBeginNum() ), nPageLimit
        ), false ) );
    }

    public void novelProfile () throws SQLException {
        String szNovel = $_GSC().optString("en_title" );

        this.mPageData.put( "NovelInfo",this.alchemist().mutual().literary().getNovelInfo(
                String.format( "WHERE `en_title` = '%s'",szNovel ), true
        ));

        this.mPageData.put( "NovelChapters",this.mysql().fetch(
                String.format( "SELECT `en_name` AS chapters FROM %s WHERE `c_novel` = '%s'",
                        this.alchemist().mutual().literary().tabNVChaptersNS(),szNovel )
                )
        );
    }
}
