package com.example.personale.firstjsonattempt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.personale.firstjsonattempt.R;
import com.example.personale.firstjsonattempt.adapter.itemtouchhelper.SimpleItemTouchHelperCallback;
import com.example.personale.firstjsonattempt.adapter.StudentAdapter;
import com.example.personale.firstjsonattempt.model.Corso;
import com.example.personale.firstjsonattempt.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by personale on 27/02/2017.
 */

public class LocalJson extends AppCompatActivity implements StudentAdapter.ClickListener{

    RecyclerView recycler;
    StudentAdapter adapter;
    private ActionMode actionMode;
    private ItemTouchHelper.Callback itemtouchhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        //Creating student adapter
        adapter = new StudentAdapter(this, this);
        fetchFromJSON();

        // Drag and drop
        itemtouchhelper = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemtouchhelper);

        //Creating and setting the recycler
        recycler = (RecyclerView) findViewById(R.id.recycler);
        touchHelper.attachToRecyclerView(recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void fetchFromJSON() {
        ArrayList<Student> tempStudent = new ArrayList<>();
        final String KEY_STUDENT = "students";
        final String KEY_COURSE = "corso";

        try {
            JSONArray jsonArray = new JSONArray(readFromJSON());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                tempStudent.add(new Student(jsonObject, new Corso(jsonObject.getJSONObject(KEY_COURSE))));
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        adapter.setDataSet(tempStudent);
    }

    public String readFromJSON() {
        final String ENCODING = "UTF-8";

        //Writer --> Abstract class for write to character streams
        //StringWriter --> Used to construct a string
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try (InputStream is = getResources().openRawResource(R.raw.students_v2)) {
            //Reader --> Abstract class for reading character streams
            Reader reader = new BufferedReader(new InputStreamReader(is, ENCODING));
            int n;

            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return writer.toString();
    }

    @Override
    public void OnClickListener(int pos, boolean startSelection) {
        if(startSelection){
            adapter.setSelected(pos);

            if(adapter.getSize() == 0){
                finishActionMode();
            }
        }
    }

    @Override
    public void OnLongClickListener(int pos, boolean startSelection) {
        if(pos == -1){
            finishActionMode();
        } else if(actionMode == null || !startSelection) {
            this.startSupportActionMode(callbackActionMode);
            adapter.setSelected(pos);
        } else {
            actionMode = null;
        }

        adapter.setStartSelection(!startSelection);
    }

    final private ActionMode.Callback callbackActionMode = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.menu_mode, menu);
            actionMode = mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.mode_delete:
                    adapter.delete();
                    break;
            }

            finishActionMode();

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    };

    public void finishActionMode(){
        adapter.clearSelection();

        if(actionMode != null){
            actionMode.finish();
            actionMode = null;
        }
        adapter.setStartSelection(false);
    }
}
