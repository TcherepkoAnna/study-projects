package org.ibmt.diplom.catcareproject.ws;

import org.ksoap2.serialization.*;

public abstract class BaseObject implements KvmSerializable {

    protected static final String NAMESPACE = "http://ws.diploma.anna.org/";

    public BaseObject()
    {
        super();
    }

}
