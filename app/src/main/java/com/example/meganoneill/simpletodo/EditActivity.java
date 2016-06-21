package com.example.meganoneill.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends AppCompatActivity {
    Integer itemPos;
    private final int RESULT_OK = 100;
    String priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        String item = getIntent().getStringExtra("item");
        priority = getIntent().getStringExtra("priority");
        edtTxt.setText(item);
        itemPos = getIntent().getIntExtra("position", 0);

        //setup the priority dropdown
        Spinner dropdown = (Spinner)findViewById(R.id.pri_spinner);
        String[] items = new String[]{"High", "Medium", "Low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }

    public void onSubmit(View v){
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        Spinner spinner = (Spinner)findViewById(R.id.pri_spinner);
        String spinner_text = spinner.getSelectedItem().toString();
        Intent data = new Intent();
        data.putExtra("item", edtTxt.getText().toString());
        data.putExtra("priority", spinner_text);
        data.putExtra("position", itemPos);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
