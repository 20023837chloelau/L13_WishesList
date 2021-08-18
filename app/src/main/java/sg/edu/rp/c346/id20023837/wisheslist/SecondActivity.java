package sg.edu.rp.c346.id20023837.wisheslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<item> itemList;
    String moduleCode;
    Button filterBtn, addBtn;

    ArrayList<String> location;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        itemList.clear();
        itemList.addAll(dbh.getAllItems());
        adapter.notifyDataSetChanged();

        location.clear();
        location.addAll(dbh.getLocation());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title));

        lv = (ListView) this.findViewById(R.id.lv);
        filterBtn = (Button) this.findViewById(R.id.filterBtn);
        addBtn = (Button) this.findViewById(R.id.addBtn);
        spinner = (Spinner) this.findViewById(R.id.spinner);

        DBHelper dbh = new DBHelper(this);
        itemList = dbh.getAllItems();
        location = dbh.getLocation();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, itemList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("item", itemList.get(position));
                startActivity(i);
            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                itemList.clear();
                itemList.addAll(dbh.getAllItemsByStars(5));
                adapter.notifyDataSetChanged();
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, location);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                itemList.clear();
                itemList.addAll(dbh.getAllItemsByLocation(String.valueOf(location.get(position))));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(SecondActivity.this, FourthActivity.class);
                startActivity(i);
            }
        });
    }
}