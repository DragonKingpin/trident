package com.sauron.saurye.elements.user.Profile;

import com.sauron.saurye.peripheral.Skill.MultipleSkill;
import com.sauron.saurye.system.PredatorArchWizardum;
import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.user.CoachShardedLayer;


public class ProfileFileOperator extends CoachShardedLayer implements EpitomeElement {
    public ProfileFileOperator(Profile stereotype ){
        super( stereotype );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Profile stereotype() {
        return (Profile) this.mStereotype;
    }


    public MultipleSkill avatarUploader(PredatorArchWizardum soul ){
        return this.coach().soulCoach( soul ).learned(
                "FileUploadSkill",
                "saveLocation", this.stereotype().astAvatarSrc()
        );
    }

}