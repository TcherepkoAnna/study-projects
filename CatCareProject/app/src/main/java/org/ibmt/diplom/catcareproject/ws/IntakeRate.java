package org.ibmt.diplom.catcareproject.ws;

import java.util.*;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

public class IntakeRate extends BaseObject {

     public int age;
     public double carbs;
     public String condition;
     public double energy;
     public double fats;
     public String gender;
     public int id;
     public String name;
     public double proteins;
     public String sex;
     public double weight;


    public Object getProperty(int index)
    {
    	switch (index)
    	{
           case 0: 
                return age; 
           case 1: 
                return carbs; 
           case 2: 
                return condition; 
           case 3: 
                return energy; 
           case 4: 
                return fats; 
           case 5: 
                return gender; 
           case 6: 
                return id; 
           case 7: 
                return name; 
           case 8: 
                return proteins; 
           case 9: 
                return sex; 
           case 10: 
                return weight; 

    	}

    	return null;
    }

    public int getPropertyCount()
    {
        return 11;
    }

    @SuppressWarnings("unchecked")
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
    	//info.type = PropertyInfo.STRING_CLASS;
    	switch (index) {

           case 0: 
                info.name = "age"; 
                info.type = PropertyInfo.INTEGER_CLASS; 
                             break; 
           case 1: 
                info.name = "carbs"; 
                info.type = Double.class;
                             break; 
           case 2: 
                info.name = "condition"; 
                info.type = new String().getClass(); 
                             break; 
           case 3: 
                info.name = "energy"; 
                info.type = Double.class;
                             break; 
           case 4: 
                info.name = "fats"; 
                info.type = Double.class;
                             break; 
           case 5: 
                info.name = "gender"; 
                info.type = new String().getClass(); 
                             break; 
           case 6: 
                info.name = "id"; 
                info.type = PropertyInfo.INTEGER_CLASS; 
                             break; 
           case 7: 
                info.name = "name"; 
                info.type = new String().getClass(); 
                             break; 
           case 8: 
                info.name = "proteins"; 
                info.type = Double.class;
                             break; 
           case 9: 
                info.name = "sex"; 
                info.type = new String().getClass(); 
                             break; 
           case 10: 
                info.name = "weight"; 
                info.type = Double.class;
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
                age = Integer.parseInt(value.toString()); 
                  break; 
           case 1:
               carbs = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break;
           case 2: 
                condition = (String)value; 
                  break; 
           case 3: 
                energy = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 4: 
                fats = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 5: 
                gender = (String)value; 
                  break; 
           case 6: 
                id = Integer.parseInt(value.toString()); 
                  break; 
           case 7: 
                name = (String)value; 
                  break; 
           case 8: 
                proteins = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 
           case 9: 
                sex = (String)value; 
                  break; 
           case 10: 
                weight = Double.valueOf((String)((SoapPrimitive) value).getValue());
                  break; 

    	}

    }

    public void register(SoapSerializationEnvelope envelope)
    {
        envelope.addMapping(NAMESPACE, "intakeRate", this.getClass());
        
    }

}
