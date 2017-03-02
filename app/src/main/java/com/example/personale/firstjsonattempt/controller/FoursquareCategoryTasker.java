package com.example.personale.firstjsonattempt.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.personale.firstjsonattempt.controller.list.CategoryList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by personale on 02/03/2017.
 */

public class FoursquareCategoryTasker extends AsyncTask<String, Void, Map<String, String>> {

    private final Context context;
    private final Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private FoursquareAPI foursquareAPI;
    private final String KEY_CATEGORY = "categories";
    private final String KEY_RESPONSE = "response";
    private final String KEY_ID = "id";
    private final String KEY_NAME = "name";
    private CategoryList categoryList;

    public FoursquareCategoryTasker(Context context, ArrayAdapter<String> spinnerAdapter, Spinner spinner, CategoryList categoryList) {
        this.categoryList = categoryList;
        this.context = context;
        this.spinnerAdapter = spinnerAdapter;
        this.spinner = spinner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Map<String, String> stringStringMap) {
        super.onPostExecute(stringStringMap);

        spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, createList(stringStringMap));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private ArrayList<String> createList(Map<String, String> stringStringMap) {
        ArrayList<String> result = new ArrayList<>();

        for (String s : stringStringMap.values()) {
            result.add(s);
        }

        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Map<String, String> doInBackground(String... params) {
        try {
            foursquareAPI = new FoursquareAPI();
            String url = foursquareAPI.createCategory();
            JSONObject jsonObject = foursquareAPI.getJSONFromCategory(url);
            JSONArray jsonCategory = jsonObject
                    .getJSONObject(KEY_RESPONSE)
                    .getJSONArray(KEY_CATEGORY);

            for (int i = 0; i < jsonCategory.length(); i++) {
                JSONObject jsonSingleCategory = jsonCategory.getJSONObject(i);
                categoryList.add(jsonSingleCategory.optString(KEY_ID, ""), jsonSingleCategory.optString(KEY_NAME, ""));
            }

        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }

        return categoryList.get();
    }
}
