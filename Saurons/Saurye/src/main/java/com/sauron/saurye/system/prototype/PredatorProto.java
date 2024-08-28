package com.sauron.saurye.system.prototype;

import com.pinecone.summer.prototype.JSONBasedControl;
import com.pinecone.summer.prototype.Pagesion;
import com.pinecone.summer.prototype.Wizard;
import com.sauron.saurye.system.PredatorArchWizardum;

import javax.servlet.http.HttpServletRequest;

public class PredatorProto {
    /** Soul **/
    public static PredatorArchWizardum mySoul(HttpServletRequest request ){
        return (PredatorArchWizardum) request.getAttribute("Prototype");
    }

    public static Wizard wizard( HttpServletRequest request ){
        return (Wizard) request.getAttribute("Prototype");
    }

    public static Pagesion model(HttpServletRequest request ){
        return (Pagesion) request.getAttribute("Prototype");
    }

    public static JSONBasedControl controls( HttpServletRequest request ){
        return (JSONBasedControl) request.getAttribute("Prototype");
    }

    public static String jspMyName( Object that ){
        try {
            return that.getClass().getSimpleName().split("_")[0];
        }
        catch ( Exception E ){
            return "";
        }
    }
}
