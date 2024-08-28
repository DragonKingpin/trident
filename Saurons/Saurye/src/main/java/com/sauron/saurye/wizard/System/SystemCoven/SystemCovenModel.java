package com.sauron.saurye.wizard.System.SystemCoven;

import com.pinecone.summer.ArchConnection;
import com.pinecone.summer.prototype.Pagesion;
import com.pinecone.summer.prototype.ModelEnchanter;

import java.sql.SQLException;

@ModelEnchanter
public class SystemCovenModel extends SystemCoven implements Pagesion {
    public SystemCovenModel( ArchConnection connection ){
        super(connection);
    }

    @Override
    protected void appendDefaultPageData(){

    }

    @Override
    @ModelEnchanter(false)
    public void defaultGenie() throws Exception {
        super.defaultGenie();
        this.writer().print( "undefined" );
    }

    @ModelEnchanter(false)
    public void getAllBandLevels() throws SQLException {
        this.writer().print( this.mysql().fetch(
                "SELECT `g_name`, `g_nickname` FROM " + this.alchemist().mutual().glossary().tabGlossaryNS() + " WHERE `gt_name` = 'band'" )
        );
    }

}
