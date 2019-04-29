package org.ibmt.diplom.catcareproject.ws;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class GetFoodsResponse extends BaseObject {

    public List<Food> returnFood = new ArrayList<Food>();;


    public Object getProperty(int index)
    {
    	return returnFood.get(index);
    }

    public List<Food> getProperties()
    {
        return returnFood;
    }

    public int getPropertyCount()
    {
        return 1;
    }

    @SuppressWarnings("unchecked")
	public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info)
    {

    	info.name = "return";
        info.type = new Food().getClass();

    }

    public void setProperty(int index, Object value)
    {

        returnFood.add((Food)value);

    }

    public void register(SoapSerializationEnvelope envelope) {
        envelope.addMapping(NAMESPACE, "getFoodsResponse", this.getClass());
        new Food().register(envelope);
    }

}
