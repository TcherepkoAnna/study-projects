package org.ibmt.diplom.catcareproject.ws;

import org.ksoap2.serialization.SoapObject;

public class GetFoods
{
    private static final String METHOD_NAME = "getFoods";
    private static final String NAMESPACE = "http://ws.diploma.anna.org/";



	public SoapObject getSoapParams()
	{
         SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


         return request;
	}

	public String getSoapAction()
	{
		return NAMESPACE + METHOD_NAME;
	}

}
