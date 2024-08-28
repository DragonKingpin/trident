package com.sauron.saurye.elements.mutual.Extend;

import com.sauron.saurye.elements.StereotypicalElement;
import com.sauron.saurye.elements.mutual.MutualAlchemist;
import com.sauron.saurye.elements.mutual.OwnedElement;

public class Extend extends OwnedElement implements StereotypicalElement {
    protected String mTabSynDiscrClans                 = "";
    protected String mTabSynDiscrClanDefs              = "";
    protected String mTabSynDiscrEpitomes              = "";

    public Extend ( MutualAlchemist alchemist ){
        super( alchemist );
        this.tableJavaify( this, this.mTableProto );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }


    public String tabSynDiscrClans        (){ return this.mTabSynDiscrClans; }
    public String tabSynDiscrClanDefs     (){ return this.mTabSynDiscrClanDefs; }
    public String tabSynDiscrEpitomes     (){ return this.mTabSynDiscrEpitomes; }

    public String tabSynDiscrClansNS      (){ return this.tableName( this.mTabSynDiscrClans ); }
    public String tabSynDiscrClanDefsNS   (){ return this.tableName( this.mTabSynDiscrClanDefs ); }
    public String tabSynDiscrEpitomesNS   (){ return this.tableName( this.mTabSynDiscrEpitomes ); }
}
