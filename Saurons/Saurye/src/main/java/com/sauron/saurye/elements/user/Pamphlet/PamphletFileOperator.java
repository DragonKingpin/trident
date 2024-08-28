package com.sauron.saurye.elements.user.Pamphlet;

import com.sauron.saurye.peripheral.Skill.MultipleSkill;
import com.sauron.saurye.system.PredatorArchWizardum;
import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.user.CoachShardedLayer;

public class PamphletFileOperator extends CoachShardedLayer implements EpitomeElement {
    public PamphletFileOperator( Pamphlet stereotype ){
        super( stereotype );
    }

    @Override
    public String elementName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Pamphlet stereotype() {
        return (Pamphlet) this.mStereotype;
    }


    public MultipleSkill coverUploader(PredatorArchWizardum soul ) {
        PamphletIncarnation hProto = (PamphletIncarnation) soul;

        return this.coach().soulCoach( soul ).learned(
                "FileUploadSkill",
                "saveLocation", hProto.protoIncarnated().astCoverSrc()
        );
    }

}
