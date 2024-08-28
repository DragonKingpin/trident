package com.sauron.saurye.elements.mutual;

import com.pinecone.framework.util.mysql.MySQLExecutor;
import com.sauron.saurye.elements.AlchemistMaster;

public abstract class EpitomeSharded implements MutualEpitomeElement {
    protected OwnedElement mStereotype = null;

    protected EpitomeSharded( OwnedElement stereotype ){
        this.mStereotype = stereotype;
    }

    @Override
    public MySQLExecutor mysql(){
        return this.mStereotype.mysql();
    }

    @Override
    public MutualAlchemist owned() {
        return this.mStereotype.mAlchemist;
    }

    @Override
    public AlchemistMaster master(){
        return this.owned().getMaster();
    }

}
