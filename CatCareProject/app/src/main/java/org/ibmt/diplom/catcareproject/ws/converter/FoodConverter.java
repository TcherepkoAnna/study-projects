package org.ibmt.diplom.catcareproject.ws.converter;

import org.ibmt.diplom.catcareproject.ws.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 06.03.2017.
 */
public class FoodConverter {

    public org.ibmt.diplom.catcareproject.model.Food convert (Food source) {

        org.ibmt.diplom.catcareproject.model.Food result = new org.ibmt.diplom.catcareproject.model.Food();
        result.setCarbs((double)source.getProperty(0));
        result.setEnergy((double)source.getProperty(1));
        result.setFats((double)source.getProperty(2));
        result.setName((String)source.getProperty(4));
        result.setProteins((double)source.getProperty(5));
        result.setState((String)source.getProperty(6));
        return result;
    }

    public List<org.ibmt.diplom.catcareproject.model.Food> convert (List<Food> sourceList) {

        List<org.ibmt.diplom.catcareproject.model.Food> resultList = new ArrayList<>(sourceList.size());

        for(Food source : sourceList) {
            resultList.add(convert(source));
        }
        return resultList;
    }

}
