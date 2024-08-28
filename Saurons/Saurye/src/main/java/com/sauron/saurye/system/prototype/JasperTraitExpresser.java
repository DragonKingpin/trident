package com.sauron.saurye.system.prototype;

import com.pinecone.summer.prototype.Wizardum;

import javax.servlet.ServletException;
import java.io.IOException;

public interface JasperTraitExpresser extends Wizardum {
    void setPhenotypicTrait( boolean bDominant );

    boolean isJasperDominant();

    String jspTPLRender( String szJSPSimpleName ) throws ServletException, IOException;

    boolean isDefaultJSPModifier();

    void appendDefaultAttribute( String key, Object value );

}
