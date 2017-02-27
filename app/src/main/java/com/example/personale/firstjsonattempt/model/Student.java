package com.example.personale.firstjsonattempt.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by personale on 27/02/2017.
 */

public class Student {
    private String name, email, github, avatar;
    private Corso corso;

    private final String KEY_NAME = "nome";
    private final String KEY_MAIL = "email";
    private final String KEY_GITHUB = "github";
    private final String KEY_AVATAR = "avatar";

    public Student(JSONObject jsonObjectStudente, Corso corso){
        try{
            name = jsonObjectStudente.getString(KEY_NAME);
            email = jsonObjectStudente.getString(KEY_MAIL);
            github = jsonObjectStudente.optString(KEY_GITHUB, "");
            avatar = jsonObjectStudente.optString(KEY_AVATAR, "");
        }catch(JSONException je){
            je.printStackTrace();
        }

        this.corso = corso;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public String buildGithubUrl(){
        final String urlGithub = "https://github.com/";

        return this.github.replace("@", urlGithub);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
