package com.sauron.saurye.elements.user;

import com.sauron.saurye.peripheral.Skill.CoreCoach;
import com.sauron.saurye.elements.SkillBasedElement;

public abstract class CoachShardedLayer extends EpitomeSharded implements SkillBasedElement {
    protected CoachShardedLayer( OwnedElement stereotype ){
        super( stereotype );
    }

    @Override
    public CoreCoach coach(){
        return this.mStereotype.owned().getMaster().host().coach();
    }

}
