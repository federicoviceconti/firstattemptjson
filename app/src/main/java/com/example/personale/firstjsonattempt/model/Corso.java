package com.example.personale.firstjsonattempt.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by personale on 27/02/2017.
 */

public class Corso {
    private final String KEY_ID = "id", KEY_CORSO = "nome";

    private int id;
    private String corso;

    public Corso(JSONObject jsonObject){
        try {
            id = jsonObject.getInt(KEY_ID);
            corso = jsonObject.optString(KEY_CORSO, "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }
}
