package com.sauron.saurye.system.legacy;

import com.pinecone.framework.util.mysql.MySQLExecutor;
import com.pinecone.framework.util.mysql.MySQLHost;
import com.pinecone.framework.util.rdb.DirectResultSession;
import com.pinecone.framework.util.rdb.ResultSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SauronMySQLExecutor extends MySQLExecutor {
    private String      mszTableNamespace;
    private String      mszLastSQLSentence     = ""   ;

    public SauronMySQLExecutor( MySQLHost mySQLHost ){
        super( mySQLHost );
    }

    public String tableName( String szSimpleTable ){
        return this.mszTableNamespace + szSimpleTable;
    }

    public void setTableNamespace( String szTableNamespace ){
        this.mszTableNamespace = szTableNamespace;
    }

    public String getLastSQLSentence() {
        return this.mszLastSQLSentence;
    }

    @Override
    public ResultSession query( String szSQL ) throws SQLException {
        this.mszLastSQLSentence = szSQL;
        return super.query( szSQL );
    }

    @Override
    public long execute( String szSQL, boolean bIgnoreNoAffected ) throws SQLException {
        this.mszLastSQLSentence = szSQL;
        return super.execute( szSQL, bIgnoreNoAffected );
    }

    @Override
    public long execute( String szSQL ) throws SQLException {
        this.mszLastSQLSentence = szSQL;
        return super.execute( szSQL );
    }
}
