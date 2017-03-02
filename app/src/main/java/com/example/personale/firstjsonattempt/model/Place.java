package com.example.personale.firstjsonattempt.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by personale on 02/03/2017.
 */
public class Place {
    private String name, address, country, phone;
    private double lat, lng;
    private final String KEY_NAME = "name",
            KEY_ADDRESS = "address",
            KEY_COUNTRY = "country",
            KEY_LAT = "lat",
            KEY_LOCATION = "location",
            KEY_CONTACT = "contact",
            KEY_PHONE = "phone",
            KEY_LNG = "lng";

    public Place(JSONObject jsonVenues){
        try {
            name = jsonVenues.getString(KEY_NAME);
            country = jsonVenues.getJSONObject(KEY_LOCATION).optString(KEY_COUNTRY);
            lat = jsonVenues.getJSONObject(KEY_LOCATION).optDouble(KEY_LAT, 0.0);
            lng = jsonVenues.getJSONObject(KEY_LOCATION).optDouble(KEY_LNG, 0.0);
            address = jsonVenues.getJSONObject(KEY_LOCATION).optString(KEY_ADDRESS, "");
            phone = jsonVenues.getJSONObject(KEY_CONTACT).optString(KEY_PHONE, "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
