package com.example.meganoneill.simpletodo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {
    Integer itemPos;
    private final int RESULT_OK = 100;
    String priority;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        String item = getIntent().getStringExtra("item");
        priority = getIntent().getStringExtra("priority");
        edtTxt.setText(item);
        itemPos = getIntent().getIntExtra("position", 0);
        Item the_item = Item.find(Item.class, "name = ?", item).get(0);

        //setup the priority dropdown
        Spinner dropdown = (Spinner)findViewById(R.id.pri_spinner);
        String[] items = new String[]{"High", "Medium", "Low"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        TextView dateTxt = (TextView) findViewById(R.id.textView3);
        dateTxt.setText(the_item.deadline);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void onSubmit(View v){
        EditText edtTxt = (EditText) findViewById(R.id.edtTxt);
        Spinner spinner = (Spinner)findViewById(R.id.pri_spinner);
        String spinner_text = spinner.getSelectedItem().toString();
        String new_date = dateView.getText().toString();
        Intent data = new Intent();
        data.putExtra("item", edtTxt.getText().toString());
        data.putExtra("deadline", new_date);
        data.putExtra("priority", spinner_text);
        data.putExtra("position", itemPos);
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
