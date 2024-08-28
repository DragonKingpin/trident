package com.sauron.saurye.elements;

import com.sauron.saurye.peripheral.Skill.CoreCoach;
import com.sauron.saurye.system.prototype.Element;

public interface SkillBasedElement extends Element {
    CoreCoach coach();
}
