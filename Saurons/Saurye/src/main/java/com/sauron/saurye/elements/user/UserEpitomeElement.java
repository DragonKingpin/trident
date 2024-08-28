package com.sauron.saurye.elements.user;

import com.sauron.saurye.elements.AlchemistMaster;
import com.sauron.saurye.elements.EpitomeElement;
import com.sauron.saurye.elements.MySQLBasedElement;

public interface UserEpitomeElement extends MySQLBasedElement, EpitomeElement {
    UserAlchemist owned();

    AlchemistMaster master();
}