package com.agarwal.arpit.easyfly.flighdataitemclasses;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.List;

/**
 * Created by arpitagarwal on 11/09/17.
 * Implemented comparable for sorting
 */

public class FlightItemDao implements Comparable<FlightItemDao> {

    private String originCode;
    private String destinationCode;
    private String departureTime;
    private String arrivalTime;
    private List<FareItemDao> fares;
    private String airlineCode;
    private String className;

    //This will save the min fare out of all the given providers
    private String minFare;


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

    public String getMinFare() {
        return minFare;
    }

    public void setMinFare(String minFare) {
        this.minFare = minFare;
    }


    @Override
    public int compareTo(@NonNull FlightItemDao flightItemDao) {
        return 0;
    }


    /**
     * Ascending comparators for Dep time
     */
    public static Comparator<FlightItemDao> COMPARATOR_DEP_TIME_INC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.departureTime;
                String seconddatas = other.departureTime;

                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return firstData.compareTo(secondData);
        }
    };

    /**
     * Ascending comparators for min fare
     */
    public static Comparator<FlightItemDao> COMPARATOR_MIN_FARE_INC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.minFare;
                String seconddatas = other.minFare;

                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return firstData.compareTo(secondData);
        }
    };


    /**
     * Ascending comparators for arr time
     */
    public static Comparator<FlightItemDao> COMPARATOR_ARR_TIME_INC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.arrivalTime;
                String seconddatas = other.arrivalTime;

                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return firstData.compareTo(secondData);
        }
    };


    public static Comparator<FlightItemDao> COMPARATOR_DEP_TIME_DEC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.departureTime;
                String seconddatas = other.departureTime;
                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return secondData.compareTo(firstData);
        }
    };


    public static Comparator<FlightItemDao> COMPARATOR_MIN_FARE_DEC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.minFare;
                String seconddatas = other.minFare;
                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return secondData.compareTo(firstData);
        }
    };


    public static Comparator<FlightItemDao> COMPARATOR_ARR_TIME_DEC = new Comparator<FlightItemDao>() {
        public int compare(FlightItemDao one, FlightItemDao other) {
            Double firstData = 0.00;
            Double secondData = 0.00;
            try {
                String firstDatas = one.arrivalTime;
                String seconddatas = other.arrivalTime;
                firstData = Double.parseDouble(firstDatas.replaceAll(",", ""));
                secondData = Double.parseDouble(seconddatas.replaceAll(",", ""));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return secondData.compareTo(firstData);
        }
    };
}
