package org.ibmt.diplom.catcareproject.ws;

import org.ibmt.diplom.catcareproject.Consts;
import org.ibmt.diplom.catcareproject.ws.converter.FoodConverter;
import org.ibmt.diplom.catcareproject.ws.converter.IntakeRateConverter;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;

import java.util.List;

public final class PetCareWebServiceSoap implements Consts {
    public String address = SERVLET_IP_ADDRESS;
    public boolean isDotNet = false;

    public boolean debug = false;

    public Element[] headers;
    public Marshal[] marshals;

    public PetCareWebServiceSoap() {
        marshals = new Marshal[]{ new MarshalBase64() };
    }

    private SoapSerializationEnvelope newEnvelope() {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = isDotNet;
        envelope.headerOut = headers;
        for (Marshal item : marshals) { item.register(envelope); }
        return envelope;
    }

    private Object transport(String action, SoapSerializationEnvelope envelope) throws Exception {
        HttpTransportSE transport = new HttpTransportSE (address);
        transport.debug = debug;
        try {
            transport.call(action, envelope);
        }
        catch (Exception exc) { throw exc; }
        finally {
            if (transport.debug) {
                System.err.println(transport.requestDump);
                System.err.println(transport.responseDump);
            }
        }
        return envelope.bodyIn;
    }

    public List<org.ibmt.diplom.catcareproject.model.Food> getFoods() throws Exception {
        GetFoods params = new GetFoods();
        return new FoodConverter().convert(getFoods(params).getProperties());
    }

    public List<org.ibmt.diplom.catcareproject.model.IntakeRate> getIntakeRates() throws Exception {
        GetRates params = new GetRates();
        return new IntakeRateConverter().convert(getRates(params).getProperties());
    }

    protected GetFoodsResponse getFoods(GetFoods params) throws Exception {
        SoapSerializationEnvelope envelope = newEnvelope();
        envelope.setOutputSoapObject(params.getSoapParams());
        new GetFoodsResponse().register(envelope);
        return (GetFoodsResponse)transport(params.getSoapAction(), envelope);
    }

    public GetRatesResponse getRates(GetRates params) throws Exception {
        SoapSerializationEnvelope envelope = newEnvelope();

        envelope.setOutputSoapObject(params.getSoapParams());
        new GetRatesResponse().register(envelope);
        return (GetRatesResponse)transport(params.getSoapAction(), envelope);
    }

    public static void main (String arg[]) throws Exception {
        PetCareWebServiceSoap client = new PetCareWebServiceSoap();
        GetRates ratesParams = new GetRates();
        GetRatesResponse ratesResponse = client.getRates(ratesParams);
        System.out.println(ratesResponse);


        GetFoods foodParams = new GetFoods();
        GetFoodsResponse foodsResponse = client.getFoods(foodParams );
        System.out.println(foodsResponse);
    }

}
