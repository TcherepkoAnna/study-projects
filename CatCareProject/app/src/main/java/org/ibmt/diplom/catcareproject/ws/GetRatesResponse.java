package org.ibmt.diplom.catcareproject.ws;

import java.util.*;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;


public class GetRatesResponse extends BaseObject {

    public List<IntakeRate> returnRate = new ArrayList<IntakeRate>();


    public Object getProperty(int index)
    {
    	return returnRate.get(index);
    }

    public List<IntakeRate> getProperties()
    {
        return returnRate;
    }

    public int getPropertyCount()
    {
        return 1;
    }

    @SuppressWarnings("unchecked")
	public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info)
    {

    	info.name = "return";
        info.type = new IntakeRate().getClass();

    }

    public void setProperty(int index, Object value)
    {

        returnRate.add((IntakeRate)value);

    }

    public void register(SoapSerializationEnvelope envelope) {
        envelope.addMapping(NAMESPACE, "getRatesResponse", this.getClass());
        new IntakeRate().register(envelope);
    }

}
