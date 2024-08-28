package com.sauron.saurye.elements.user;

import com.pinecone.framework.util.mysql.MySQLExecutor;
import com.sauron.saurye.elements.AlchemistMaster;

public abstract class EpitomeSharded implements UserEpitomeElement {
    protected OwnedElement mStereotype = null;

    protected EpitomeSharded( OwnedElement stereotype ){
        this.mStereotype = stereotype;
    }

    @Override
    public MySQLExecutor mysql(){
        return this.mStereotype.mysql();
    }

    @Override
    public UserAlchemist owned() {
        return this.mStereotype.mAlchemist;
    }

    @Override
    public AlchemistMaster master(){
        return this.owned().getMaster();
    }
}