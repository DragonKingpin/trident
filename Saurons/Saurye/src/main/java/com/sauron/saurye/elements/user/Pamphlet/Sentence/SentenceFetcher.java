package com.sauron.saurye.elements.user.Pamphlet.Sentence;

import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.user.EpitomeSharded;
import com.sauron.saurye.elements.user.Pamphlet.Pamphlet;

public class SentenceFetcher extends EpitomeSharded implements EpitomeElement {
    public SentenceFetcher(Pamphlet stereotype) { super(stereotype);
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Pamphlet stereotype() {
        return (Pamphlet) this.mStereotype;
    }


    private BasicSentenceList mBasicSentenceList = null;

    public BasicSentenceList basicSentenceList () {
        if( this.mBasicSentenceList == null ){
            this.mBasicSentenceList = new BasicSentenceList( this.stereotype() );
        }
        return this.mBasicSentenceList;
    }

}
