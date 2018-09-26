package com.example.tibur.myparcelable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextAge;
    String name;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextAge = findViewById(R.id.textInputEditTextAge);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        boolean error = false;

        textInputEditTextName.setError(null);
        textInputEditTextAge.setError(null);

        name = textInputEditTextName.getText().toString();
        Log.i("tiburcio", "pasó por aquí");

        try{
            age = Integer.parseInt(textInputEditTextAge.getText().toString());

            if(age < 18 || age > 100){
                textInputEditTextAge.setError(getString(R.string.error_age_range));
                textInputEditTextAge.requestFocus();
                error = true;
            }
        } catch (Exception e){
            textInputEditTextAge.setError(getString(R.string.error_age_range));
            textInputEditTextAge.requestFocus();
            error = true;
        }

        if(TextUtils.isEmpty(name)){
            textInputEditTextName.setError(getString(R.string.error_field_required));
            textInputEditTextName.requestFocus();
            error = true;
        }

        if(!error){
            sendInfoToDetailActivity();
        }
    }

    private void sendInfoToDetailActivity() {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        User user = new User(name, age);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
