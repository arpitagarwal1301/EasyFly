package com.agarwal.arpit.easyfly.flighdataitemclasses;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by arpitagarwal on 11/09/17.
 */

public class FlightItemDao extends JSONObject {

    private String originCode;
    private String destinationCode;
    private String departureTime;
    private String arrivalTime;
    private List<FareItemDao> fares;
    private String airlineCode;
    private String className;


    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<FareItemDao> getFares() {
        return fares;
    }

    public void setFares(List<FareItemDao> fares) {
        this.fares = fares;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
