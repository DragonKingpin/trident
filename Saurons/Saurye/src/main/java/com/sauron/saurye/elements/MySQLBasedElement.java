package com.sauron.saurye.elements;

import com.pinecone.framework.util.mysql.MySQLExecutor;
import com.sauron.saurye.system.prototype.Element;

public interface MySQLBasedElement extends Element {
    MySQLExecutor mysql();
}
