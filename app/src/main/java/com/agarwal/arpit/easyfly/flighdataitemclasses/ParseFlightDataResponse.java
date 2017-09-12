package com.agarwal.arpit.easyfly.flighdataitemclasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arpitagarwal on 11/09/17.
 */

public class ParseFlightDataResponse {

    private final String KEY_APPENDIX = "appendix";
    private final String KEY_AIRLINES = "airlines";
    private final String KEY_AIRPORTS = "airports";
    private final String KEY_PROVIDERS = "providers";
    private final String KEY_FLIGHTS = "flights";

    //FLIGHT ITEM KEYS
    private final String KEY_ORG_CODE = "originCode";
    private final String KEY_DES_CODE = "destinationCode";
    private final String KEY_DEP_TIME = "departureTime";
    private final String KEY_ARV_TIME = "arrivalTime";
    private final String KEY_AIR_CODE = "airlineCode";
    private final String KEY_CLASS = "class";
    private final String KEY_FARES_ARRAY = "fares";

    //FARE ITEM KEYS
    private final String KEY_PROVIDER_ID = "providerId";
    private final String KEY_FARE = "fare";


    private Map<String, String> airlineCodeMap = new HashMap<>();
    private Map<String, String> airportCodeMap = new HashMap<>();
    private Map<String, String> providersList = new HashMap<>();
    private List<FlightItemDao> flighDataItemsList = new ArrayList<>();

    public Map<String, String> getAirlineCodeMap() {
        return airlineCodeMap;
    }

    public void setAirlineCodeMap(Map<String, String> airlineCodeMap) {
        this.airlineCodeMap = airlineCodeMap;
    }

    public Map<String, String> getAirportCodeMap() {
        return airportCodeMap;
    }

    public void setAirportCodeMap(Map<String, String> airportCodeMap) {
        this.airportCodeMap = airportCodeMap;
    }

    public Map<String, String> getProvidersList() {
        return providersList;
    }

    public void setProvidersList(Map<String, String> providersList) {
        this.providersList = providersList;
    }

    public List<FlightItemDao> getFlighDataItemsList() {
        return flighDataItemsList;
    }

    public void setFlighDataItemsList(List<FlightItemDao> flighDataItemsList) {
        this.flighDataItemsList = flighDataItemsList;
    }


    public void parseResponse(JSONObject response) throws JSONException {
        if (response.has(KEY_APPENDIX)) {
            JSONObject jo = response.getJSONObject(KEY_APPENDIX);

            //Parsing airline code list
            JSONObject airlinesCode = jo.getJSONObject(KEY_AIRLINES);
            airlineCodeMap.put("SG", airlinesCode.getString("SG"));
            airlineCodeMap.put("AI", airlinesCode.getString("AI"));
            airlineCodeMap.put("G8", airlinesCode.getString("G8"));
            airlineCodeMap.put("9W", airlinesCode.getString("9W"));
            airlineCodeMap.put("6E", airlinesCode.getString("6E"));

            //parsing airport code list
            JSONObject airportCode = jo.getJSONObject(KEY_AIRPORTS);
            airportCodeMap.put("DEL", airportCode.getString("DEL"));
            airportCodeMap.put("BOM", airportCode.getString("BOM"));

            //parsing providers list
            JSONObject providers = jo.getJSONObject(KEY_PROVIDERS);
            providersList.put("1", providers.getString("1"));
            providersList.put("2", providers.getString("2"));
            providersList.put("3", providers.getString("3"));
            providersList.put("4", providers.getString("4"));
        }

        //Parsing flights data
        if (response.has(KEY_FLIGHTS)) {
            JSONArray jsonArray = response.getJSONArray(KEY_FLIGHTS);

            //Iterating throught the array of data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                FlightItemDao itemDao = new FlightItemDao();

                itemDao.setOriginCode(object.getString(KEY_ORG_CODE));
                itemDao.setDestinationCode(object.getString(KEY_DES_CODE));
                itemDao.setDepartureTime(object.getString(KEY_DEP_TIME));
                itemDao.setArrivalTime(object.getString(KEY_ARV_TIME));
                itemDao.setAirlineCode(object.getString(KEY_AIR_CODE));
                itemDao.setClassName(object.getString(KEY_CLASS));

                //Parsing Fares Data
                JSONArray array = object.getJSONArray(KEY_FARES_ARRAY);
                List<FareItemDao> list = new ArrayList<>();
                for (int j = 0; j < array.length(); j++) {
                    JSONObject fareObject = array.getJSONObject(j);
                    FareItemDao fareItemDao = new FareItemDao();
                    fareItemDao.setProviderId(fareObject.getString(KEY_PROVIDER_ID));
                    fareItemDao.setFare(fareObject.getString(KEY_FARE));

                    list.add(fareItemDao);
                }

                itemDao.setFares(list);

                //Adding the final parsed data into the list
                flighDataItemsList.add(itemDao);
            }

        }
    }


}
