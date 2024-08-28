package com.sauron.saurye.elements.mutual;

import com.sauron.saurye.elements.AlchemistMaster;
import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.MySQLBasedElement;

public interface MutualEpitomeElement extends MySQLBasedElement, EpitomeElement {
     MutualAlchemist owned();

    AlchemistMaster master();
}
