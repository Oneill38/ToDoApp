package com.example.meganoneill.simpletodo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class BasicActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private final int RESULT_ADDED = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void addNewItem(View view) {
        TextView new_name = (TextView) findViewById(R.id.nameTxt);
        String name = new_name.getText().toString();
        Spinner spinner = (Spinner)findViewById(R.id.pri_spinner);
        String priority = spinner.getSelectedItem().toString();
        TextView the_date = (TextView) findViewById(R.id.textView3);
        String new_date = the_date.getText().toString();

        Item new_item = new Item(name, priority, new_date);
        new_item.save();

        Intent data = new Intent();
        data.putExtra("item", name);
        data.putExtra("priority", priority);
        setResult(RESULT_ADDED, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
