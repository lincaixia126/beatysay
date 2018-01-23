package com.example.lin.gson;

/**
 * Created by lin on 18/1/11.
 */

public class ListMapData {


    /**
     * id : 18
     * city : test
     * street : test 1
     * zipcode : 121209
     * state : IL
     * lat : 32.158138
     * lng : 34.807838
     */

    private int id;
    private String city;
    private String street;
    private int zipcode;
    private String state;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
