package assignment1.mkamprat_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class counterList extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ListView oldCountersList;

    public ArrayList<new_counter> count = new ArrayList<new_counter>();
    public ArrayAdapter<new_counter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_list);

        Button newCountButton = (Button) findViewById(R.id.newCount);
        oldCountersList = (ListView) findViewById(R.id.oldCountersList);

        newCountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(this, Counter.class);

                startActivity(intent);
            }
        });
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
}
