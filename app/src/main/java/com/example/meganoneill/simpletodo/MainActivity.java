package com.example.meganoneill.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> items;
    ArrayAdapter<Item> itemsAdapter;
    ListView lvItems;

    private final int REQUEST_CODE = 20;
    private final int RESULT_OK = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        setUpEditListener();
    }

    private void setUpEditListener(){
        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapter, View item, int pos, long id){
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("item", items.get(pos).toString());
                    intent.putExtra("position", pos);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        );
    }

    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id){
                        Item name = (Item) lvItems.getItemAtPosition(pos);
                        items.remove(pos);
                        name.delete();
                        itemsAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View v){
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        Item new_item = new Item(itemText);
        new_item.save();
        items.add(new_item);
        itemsAdapter.notifyDataSetChanged();
        etNewItem.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            String text = data.getExtras().getString("item");
            int position = data.getExtras().getInt("position");
            Item toDo = items.get(position);
            toDo.name = text;
            toDo.save();
            items.set(position, toDo);
            itemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    private void readItems(){
        items = Item.listAll(Item.class);
    }

}
