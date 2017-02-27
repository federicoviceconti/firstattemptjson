package com.example.personale.firstjsonattempt.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.personale.firstjsonattempt.R;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating student adapter
        adapter = new StudentAdapter(this);
        fetchFromJSON();

        //Creating and setting the recycler
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void fetchFromJSON() {
        ArrayList<Student> tempStudent = new ArrayList<>();
        final String KEY_STUDENT = "students";
        final String KEY_COURSE = "corso";

        try {
            JSONObject studentsJsonObject = new JSONObject(readFromJSON());
            JSONArray studentsJsonArray = studentsJsonObject.getJSONArray(KEY_STUDENT);

            for (int i = 0; i < studentsJsonArray.length(); i++) {
                JSONObject jsonObject = studentsJsonArray.getJSONObject(i);
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
}
