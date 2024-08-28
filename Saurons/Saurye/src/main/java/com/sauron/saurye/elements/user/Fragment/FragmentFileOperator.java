package com.sauron.saurye.elements.user.Fragment;

import com.sauron.saurye.peripheral.Skill.MultipleSkill;
import com.sauron.saurye.system.PredatorArchWizardum;
import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.user.CoachShardedLayer;

public class FragmentFileOperator extends CoachShardedLayer implements EpitomeElement {
    public FragmentFileOperator(Fragment stereotype ){
        super( stereotype );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Fragment stereotype() {
        return (Fragment) this.mStereotype;
    }


    public MultipleSkill coverUploader(PredatorArchWizardum soul ){
        return this.coach().soulCoach( soul ).learned(
                "FileUploadSkill",
                "saveLocation", this.stereotype().astCoverSrc()
        );
    }

}