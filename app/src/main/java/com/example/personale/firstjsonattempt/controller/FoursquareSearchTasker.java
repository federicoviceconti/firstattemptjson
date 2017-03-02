package com.example.personale.firstjsonattempt.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.personale.firstjsonattempt.adapter.PlaceAdapter;
import com.example.personale.firstjsonattempt.model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by personale on 02/03/2017.
 */

public class FoursquareSearchTasker extends AsyncTask<String, Void, ArrayList<Place>> {

    private final Context context;
    private final PlaceAdapter adapter;
    private ProgressBar loading;
    private FoursquareAPI foursquareAPI;
    private String KEY_VENUES = "venues";
    private String KEY_RESPONSE = "response";

    public FoursquareSearchTasker(Context context, PlaceAdapter adapter) {
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = new ProgressBar(context);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Place> doInBackground(String... params) {
        ArrayList<Place> placeList = new ArrayList<>();

        try {
            foursquareAPI = new FoursquareAPI();
            String url = foursquareAPI.createQuery(params[0]);


            JSONObject jsonResponse = foursquareAPI.getJSONFromUrl(url);
            JSONArray jsonVenues = jsonResponse
                    .getJSONObject(KEY_RESPONSE)
                    .getJSONArray(KEY_VENUES);

            for (int i = 0; i < jsonVenues.length(); i++) {
                placeList.add(new Place(jsonVenues.getJSONObject(i)));
            }

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }

        return placeList;
    }

    @Override
    protected void onPostExecute(ArrayList<Place> places) {
        super.onPostExecute(places);
        adapter.setDataSet(places);
        adapter.notifyDataSetChanged();
        loading.setVisibility(View.INVISIBLE);
        loading = null;
    }
}
