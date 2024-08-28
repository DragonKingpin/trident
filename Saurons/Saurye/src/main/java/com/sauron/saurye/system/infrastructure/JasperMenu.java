package com.sauron.saurye.system.infrastructure;

import com.pinecone.framework.util.json.JSON;
import com.pinecone.framework.util.json.JSONArray;
import com.pinecone.framework.util.json.JSONMaptron;
import com.pinecone.framework.util.json.JSONObject;
import com.sauron.saurye.peripheral.Skill.CoreCoach;
import com.sauron.saurye.system.Saurye;

import java.io.IOException;

public class JasperMenu {
    private JSONObject mhMenu        = null ;

    private JSONArray mhCurrentMenu  = null ;

    private Saurye mhMatrix        = null ;


    public JasperMenu(Saurye predator ){
        this.mhMatrix = predator;
    }

    public void loadMenu(){
        String filename = this.mhMatrix.getFullMenuPath();
        try{
            this.mhMenu = new JSONMaptron( this.mhMatrix.readFileContentAll(filename) );
        }
        catch (IOException E){
            E.printStackTrace();
        }

        //Debug.trace(this.menu);
    }

    public String spawn(){
        this.loadMenu();
        this.mhCurrentMenu = CoreCoach.spawnMenuFairyWithRole( this.mhMenu );

        StringBuilder res = new StringBuilder();
        if( this.mhCurrentMenu != null ){
            for(int i=0 ; i<this.mhCurrentMenu.length(); i++){
                JSONObject row = this.mhCurrentMenu.getJSONObject(i);

                res.append( this.createSingleMenuSet(
                        row.getString("href"),
                        row.getString("title"),
                        row.getString("icon"),
                        row.get("submenu"))
                );
            }
        }
        return res.toString();
    }

    private String createSingleMenu( String szLink, String szTitle, String szIcon ){
        return "<li class=\'\'>"+
                "<a href=\'" + szLink +"\'>"+
                "<i class=\'" + szIcon  + "\'></i>"+
                "<span> " + szTitle +"</span>"+
                "</a></li>";
    }

    private String createSetMenu( String szLink, String szTitle,String szIcon, boolean bHaveRowIcon ){
        String rowICON = "";
        if( bHaveRowIcon ){
            rowICON = "fa fa-angle-down";
        }
        return "<a href=\"" + szLink + "\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+
                "<i class=\'" + szIcon +"\'></i>" +
                "<span> " + szTitle + "</span>"+
                "<i class=\"" + rowICON+ "\"></i>"+
                "</a> <ul class=\"dropdown-menu\" role=\"menu\">";
    }

    private String submenuAnalysis( Object hSubmenu ){
        StringBuilder sbSubMenu = new StringBuilder();

        if(hSubmenu != JSON.NULL){
            for(int i=0 ; i< ((JSONArray)hSubmenu).length(); i++){
                JSONObject row = ((JSONArray)hSubmenu).getJSONObject(i);

                if( !(row.get("submenu") instanceof JSONArray) ){
                    sbSubMenu.append( this.createSingleMenu(
                            row.getString("href"), row.getString("title"), row.getString("icon")
                    ) );
                }else {
                    sbSubMenu.append("<li class=\"dropdown-submenu\" >").
                            append( this.createSetMenu(row.getString("href"), row.getString("title"), row.getString("icon"),false) ).
                            append( this.submenuAnalysis(row.get("submenu")) ).append("</ul></li>");
                }
            }
        }

        return sbSubMenu.toString();
    }

    private String createSingleMenuSet( String szMainLink, String szMainTitle, String szMainIcon, Object hSubmenu ){
        if( hSubmenu != JSON.NULL ){
            return "<li class='dropdown'>"
                    + this.createSetMenu(szMainLink,szMainTitle,szMainIcon,true)
                    + this.submenuAnalysis(hSubmenu)
                    + "</ul></li>";
        }
        else{
            return this.createSingleMenu( szMainLink, szMainTitle, szMainIcon );
        }
    }

}
