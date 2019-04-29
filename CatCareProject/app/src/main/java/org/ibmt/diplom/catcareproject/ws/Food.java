package org.ibmt.diplom.catcareproject.ws;

import java.util.*;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

public class Food extends BaseObject {

     public double carbohydrates;
     public double energy;
     public double fats;
     public int id;
     public String name;
     public double proteins;
     public String state;


    public Object getProperty(int index)
    {
    	switch (index)
    	{
           case 0: 
                return carbohydrates; 
           case 1: 
                return energy; 
           case 2: 
                return fats; 
           case 3: 
                return id; 
           case 4: 
                return name; 
           case 5: 
                return proteins; 
           case 6: 
                return state; 

    	}

    	return null;
    }

    public int getPropertyCount()
    {
        return 7;
    }

    @SuppressWarnings("unchecked")
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
    	//info.type = PropertyInfo.STRING_CLASS;
    	switch (index) {

           case 0: 
                info.name = "carbohydrates"; 
                info.type = Double.class;
                             break; 
           case 1: 
                info.name = "energy"; 
                info.type = Double.class;
                             break; 
           case 2: 
                info.name = "fats"; 
                info.type = Double.class;
                             break; 
           case 3: 
                info.name = "id"; 
                info.type = PropertyInfo.INTEGER_CLASS; 
                             break; 
           case 4: 
                info.name = "name"; 
                info.type = new String().getClass(); 
                             break; 
           case 5: 
                info.name = "proteins"; 
                info.type = Double.class;
                             break; 
           case 6: 
                info.name = "state"; 
                info.type = new String().getClass(); 
                             break; 

        default:
            break;
        }
    }

    public void setProperty(int index, Object value)
    {
    	switch (index)
    	{
           case 0: 
                carbohydrates = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 1: 
                energy = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 2: 
                fats = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 3: 
                id = Integer.parseInt(value.toString()); 
                  break; 
           case 4: 
                name = (String)value; 
                  break; 
           case 5: 
                proteins = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 6: 
                state = (String)value; 
                  break; 

    	}

    }

    public void register(SoapSerializationEnvelope envelope)
    {
        envelope.addMapping(NAMESPACE, "food", this.getClass());
        
    }

}
