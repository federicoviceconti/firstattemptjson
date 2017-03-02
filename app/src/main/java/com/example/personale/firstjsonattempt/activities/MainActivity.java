package com.example.personale.firstjsonattempt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.personale.firstjsonattempt.R;

/**
 * Created by personale on 02/03/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.remote).setOnClickListener(this);
        findViewById(R.id.local).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, v.getId() == R.id.remote ? RemoteJson.class : LocalJson.class));
    }
}
