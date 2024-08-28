package com.sauron.saurye.elements.user.Word;

import com.sauron.saurye.elements.user.UserAlchemist;
import com.sauron.saurye.elements.StereotypicalElement;
import com.sauron.saurye.elements.user.OwnedElement;

public class Word extends OwnedElement implements StereotypicalElement {
    protected String mTabRecall             = "";
    protected String mTabRecord             = "";

    public Word ( UserAlchemist alchemist ) {
        super( alchemist );
        this.tableJavaify( this, this.mTableProto );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }


    public String tabRecall         (){ return this.mTabRecall; }
    public String tabRecord         (){ return this.mTabRecord; }

    public String tabRecallNS       (){ return this.tableName( this.mTabRecall ); }
    public String tabRecordNS       (){ return this.tableName( this.mTabRecord ); }

}