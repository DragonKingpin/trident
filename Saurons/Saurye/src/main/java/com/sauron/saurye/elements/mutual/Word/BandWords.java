package com.sauron.saurye.elements.mutual.Word;

import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.mutual.EpitomeSharded;

public class BandWords extends EpitomeSharded implements EpitomeElement {
    public BandWords ( Word stereotype ){
        super( stereotype );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Word stereotype() {
        return (Word) this.mStereotype;
    }

}
