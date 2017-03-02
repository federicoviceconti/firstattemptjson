package com.example.personale.firstjsonattempt.controller;

import com.example.personale.firstjsonattempt.model.Place;
import com.example.personale.firstjsonattempt.model.Student;

import java.util.ArrayList;

/**
 * Created by personale on 02/03/2017.
 */

public class PlaceList {
    private ArrayList<Place> places;

    public PlaceList(){
        places = new ArrayList<>();
    }

    public void addPlace(Place p){
        places.add(p);
    }

    public void editPlace(int pos, Place p){
        places.set(pos, p);
    }

    public void removePlace(int pos){
        if(pos < places.size())
            places.remove(pos);
    }

    public ArrayList<Place> getPlaces(){
        return places;
    }

    public Place getPlace(int pos){
        return places.get(pos);
    }

    public int getSize(){
        return places.size();
    }

    public void setDataSet(ArrayList<Place> places) {
        this.places = places;
    }
}
