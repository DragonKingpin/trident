package com.sauron.saurye.peripheral.Skill;

import com.sauron.saurye.system.PredatorArchWizardum;

public interface SoulSkill extends SpecialSkill {
    void bind( PredatorArchWizardum soul ) ;

    PredatorArchWizardum mySoul();
}
