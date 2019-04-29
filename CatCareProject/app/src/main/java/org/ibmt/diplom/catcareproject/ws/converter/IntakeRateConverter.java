package org.ibmt.diplom.catcareproject.ws.converter;

import org.ibmt.diplom.catcareproject.ws.IntakeRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 06.03.2017.
 */

public class IntakeRateConverter {

    public org.ibmt.diplom.catcareproject.model.IntakeRate convert (IntakeRate source) {

        org.ibmt.diplom.catcareproject.model.IntakeRate result = new org.ibmt.diplom.catcareproject.model.IntakeRate();
        result.setAge((int)source.getProperty(0));
        result.setCarbs((double)source.getProperty(1));
        result.setCondition((String) source.getProperty(2));
        result.setEnergy((double)source.getProperty(3));
        result.setFats((double)source.getProperty(4));
        result.setGender((String)source.getProperty(5));
        result.setName((String)source.getProperty(7));
        result.setProteins((double)source.getProperty(8));
        result.setSex((String)source.getProperty(9));
        result.setWeight((double)source.getProperty(10));
        return result;
    }

    public List<org.ibmt.diplom.catcareproject.model.IntakeRate> convert (List<IntakeRate> sourceList) {

        List<org.ibmt.diplom.catcareproject.model.IntakeRate> resultList = new ArrayList<>(sourceList.size());

        for(IntakeRate source : sourceList) {
            resultList.add(convert(source));
        }
        return resultList;
    }

}
