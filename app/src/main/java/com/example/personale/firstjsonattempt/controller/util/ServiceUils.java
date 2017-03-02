package com.example.personale.firstjsonattempt.controller.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by personale on 27/02/2017.
 */
public class ServiceUils {
    public static Bitmap downloadBitmap(String url) {
        //Create an httpurlconnection object
        HttpURLConnection urlConnection = null;

        try{
            //Create a Url
            URL uri = new URL(url);
            //Connection to the remote object referenced by url
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();

            if(statusCode != 200){
                return null;
            }

            //Readed stream by open url connection
            InputStream inputStream = urlConnection.getInputStream();

            if(inputStream != null){
                //decodestream --> decode input stream into a bitmap
                return BitmapFactory.decodeStream(inputStream);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }
}
