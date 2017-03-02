package com.example.personale.firstjsonattempt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.personale.firstjsonattempt.R;
import com.example.personale.firstjsonattempt.adapter.PlaceAdapter;
import com.example.personale.firstjsonattempt.controller.list.CategoryList;
import com.example.personale.firstjsonattempt.controller.FoursquareCategoryTasker;
import com.example.personale.firstjsonattempt.controller.FoursquareSearchTasker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by personale on 02/03/2017.
 */

public class RemoteJson extends AppCompatActivity implements View.OnClickListener {

    private Button okBtn;
    private AutoCompleteTextView inputUser;
    private Spinner spinner;
    private RecyclerView recycler;
    private PlaceAdapter adapter;
    private List<String> itemSearched;
    private ArrayAdapter<String> autoCompleteTextAdapter;
    private ArrayAdapter<String> spinnerAdapter;
    private CategoryList categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        spinner = (Spinner) findViewById(R.id.category_spinner);

        itemSearched = new ArrayList<>();
        categoryList = new CategoryList();
        loadItemCategory();
        
        autoCompleteTextAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, itemSearched);
        inputUser = (AutoCompleteTextView) findViewById(R.id.input_user);
        inputUser.setAdapter(autoCompleteTextAdapter);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter = new PlaceAdapter(this));

        okBtn = (Button) findViewById(R.id.remote_search);
        okBtn.setOnClickListener(this);

    }

    private void loadItemCategory() {
        new FoursquareCategoryTasker(this, spinnerAdapter, spinner, categoryList).execute();
    }

    private String searchCategory(String selectedItem) {

        for(Map.Entry<String, String> single : categoryList.get().entrySet()){

            if(selectedItem.equalsIgnoreCase(single.getValue())){
                return single.getKey();
            }
        }

        return null;
    }

    @Override
    public void onClick(View v) {
        String itemSearched = inputUser.getText().toString();
        String categorySearched = searchCategory(spinner.getSelectedItem().toString());

        new FoursquareSearchTasker(this, adapter).execute(itemSearched, categorySearched);
        addToComplete(itemSearched);
    }

    private void addToComplete(String inputUser) {
        autoCompleteTextAdapter.add(inputUser);
    }
}
