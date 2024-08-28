package com.sauron.saurye.elements.user.Pamphlet.Glossary;

import com.sauron.saurye.elements.user.Pamphlet.Pamphlet;

public class Glossary extends Pamphlet {
    protected Pamphlet mParent                  ;

    protected String   mTabWords              = "";
    protected String   mTabRecitePlan         = "";

    public Glossary ( Pamphlet parent ){
        super( parent );
        this.tableJavaify( this, this.mTableProto );
        this.assetJavaify( this, this.mAssetProto );

        this.mParent = parent;
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }


    public String tabWords          (){ return this.mTabWords; }
    public String tabRecitePlan     (){ return this.mTabRecitePlan; }

    public String tabWordsNS        (){ return this.tableName( this.mTabWords ); }
    public String tabRecitePlanNS   (){ return this.tableName( this.mTabRecitePlan ); }





    /** Asset **/
    protected String               mAstCoverSrc    = "";
    protected WordFetcher          mWordFetcher    = null;

    public String                  astCoverSrc       (){ return this.mAstCoverSrc; }

    public WordFetcher             wordFetcher       () {
        if( this.mWordFetcher == null ){
            this.mWordFetcher = new WordFetcher( this );
        }
        return this.mWordFetcher;
    }
}
