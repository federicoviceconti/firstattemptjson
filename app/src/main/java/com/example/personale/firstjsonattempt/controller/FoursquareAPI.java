package com.example.personale.firstjsonattempt.controller;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by personale on 02/03/2017.
 */

public class FoursquareAPI {
    private static final String BASE_URL = "https://api.foursquare.com/v2/venues/search?v=20161016&near=rome&query=";
    private static final String TOKEN = "&client_id=PRNHPU011KKTDFQUMCP3BGHF3K0532MFRTN5VJAVD4KTVVDM&client_secret=0NRKK422MKHOAAI31C524G4LFV41ADGMKGOIF2MONVW4X2GB";
    private final String HTTPMETHOD = "GET";

    JSONObject getJSONFromUrl(String queryUrl) throws IOException, JSONException {

        URL url = new URL(queryUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(HTTPMETHOD);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();

        return new JSONObject(createJson(url));
    }

    private String createJson(URL url) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        Log.d("Async", sb.toString());
        return sb.toString();
    }

    String createQuery(String query) {
        return BASE_URL + query + TOKEN;
    }
}
