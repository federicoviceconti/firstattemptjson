package com.example.personale.firstjsonattempt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.personale.firstjsonattempt.R;
import com.example.personale.firstjsonattempt.adapter.PlaceAdapter;
import com.example.personale.firstjsonattempt.controller.FoursquareSearchTasker;

/**
 * Created by personale on 02/03/2017.
 */

public class RemoteJson extends AppCompatActivity implements View.OnClickListener {

    private Button okBtn;
    private EditText inputUser;
    private RecyclerView recycler;
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        inputUser = (EditText) findViewById(R.id.user_input);
        okBtn = (Button) findViewById(R.id.remote_search);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter = new PlaceAdapter(this));
        okBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new FoursquareSearchTasker(this, adapter).execute(inputUser.getText().toString());
    }
}
