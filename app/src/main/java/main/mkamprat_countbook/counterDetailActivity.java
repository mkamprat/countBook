package main.mkamprat_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import android.widget.EditText;
import android.support.v4.app.Fragment;

/**
 * An activity representing a single counter detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link counterListActivity}.
 */
public class counterDetailActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText startingValueText;
    private EditText currentValueText;
    private EditText counterCommentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        nameText = (EditText) findViewById(R.id.counterNameText);
        startingValueText = (EditText) findViewById(R.id.startingValueText);
        currentValueText = (EditText) findViewById(R.id.currentValueText);
        counterCommentText = (EditText) findViewById(R.id.counterCommentText);

        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String counterName = nameText.getText().toString();
                int startingValue = Integer.parseInt(startingValueText.getText().toString());
                int currentValue = Integer.parseInt(currentValueText.getText().toString());
                String counterComment = counterCommentText.getText().toString();

                Bundle arguments = new Bundle();
                arguments.putString(counterDetailFragment.ARG_ITEM_ID,
                        getIntent().getStringExtra(counterDetailFragment.ARG_ITEM_ID));
                counterDetailFragment fragment = new counterDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction();
                fragment.editCounter(counterName, startingValue, currentValue, counterComment);
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(counterDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(counterDetailFragment.ARG_ITEM_ID));
            counterDetailFragment fragment = new counterDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.counter_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, counterListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
