package com.sauron.saurye.wizard.Admin.AdminMutualGlossary;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.sauron.saurye.system.Saurye;
import com.sauron.saurye.system.prototype.JasperModifier;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

@JasperModifier
public class AdminMutualGlossaryModel extends AdminMutualGlossary implements Pagesion {
    public AdminMutualGlossaryModel( ArchConnection connection ) {
        super(connection);
    }


    @Override
    public void defaultGenie() throws Exception {
        super.defaultGenie();
        this.mutualGlossaryList();
    }

    public void mutualGlossaryList() throws SQLException {
        String szModularBasicSQL = String.format(
                "SELECT %%s FROM %s",
                this.tableName( Saurye.TABLE_MUTUAL_GLOSSARY )
        );

        this.coach().model().simplePaginationPreTreat( this, szModularBasicSQL,"" );

        this.mPageData.put(
                "GlossaryList", this.mysql().fetch(
                        String.format(
                                szModularBasicSQL ,
                                "*"
                                )
                        )
        );
    }

    public void appendNewGlossary(){

    }

    public void glossaryEditor() throws IOException, ServletException, SQLException {
        String szModularBasicSQL = String.format(
                "SELECT %%s FROM %s",
                this.tableName( Saurye.TABLE_MUTUAL_GLOSSARY )
        );

        this.mPageData.put(
                "CurrentGlossaryInfo", this.assertSelectResult(this.mysql().fetch(
                        String.format(
                                szModularBasicSQL ,
                                "*",
                                " WHERE class_id = '" + this.assertString( this.$_GSC().optString("class_id") ) + "'"
                        )
                        )
                ).optJSONObject(0)
        );
    }

}