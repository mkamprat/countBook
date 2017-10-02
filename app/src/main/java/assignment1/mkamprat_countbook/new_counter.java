package assignment1.mkamprat_countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class new_counter extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ListView oldCountersList;
    public ArrayList<new_counter> count = new ArrayList<new_counter>();
    public ArrayAdapter<new_counter> adapter;

    private EditText countName;
    private EditText countComments;
    private EditText countInitVal;
    private EditText countCurrentVal;
    private EditText countDate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);
        countName = (EditText) findViewById(R.id.counterName);
        countComments = (EditText) findViewById(R.id.counterComments);
        countInitVal = (EditText) findViewById(R.id.counterInitVal);
        countCurrentVal = (EditText) findViewById(R.id.counterCurrentVal);
        Button createButton = (Button) findViewById(R.id.counterCreate);

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String counterName = countName.getText().toString();
                String counterComments = countComments.getText().toString();
                String counterInitVal = countInitVal.getText().toString();
                String counterCurrentVal = countCurrentVal.getText().toString();
                count.add(new Counter(counterName, counterComments, counterInitVal, counterCurrentVal));
                adapter.notifyDataSetChanged();
                saveInFile();
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        loadFromFile();

        adapter = new ArrayAdapter<new_counter>(this,
                R.layout.list_counter, count);
        oldCountersList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            count = gson.fromJson(in,listType);
        } catch (FileNotFoundException e) {
            count = new ArrayList<new_counter>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(count, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
