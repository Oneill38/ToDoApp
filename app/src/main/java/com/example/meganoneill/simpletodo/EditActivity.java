package com.example.meganoneill.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Integer itemPos;
    private final int RESULT_OK = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        String item = getIntent().getStringExtra("item");
        edtTxt.setText(item);
        itemPos = getIntent().getIntExtra("position", 0);
    }

    public void onSubmit(View v){
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        Intent data = new Intent();
        data.putExtra("item", edtTxt.getText().toString());
        data.putExtra("position", itemPos);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
