package sg.edu.rp.c346.id20023837.wisheslist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    Button listBtn, insertBtn;
    EditText etName, etLocation, etPrice;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        listBtn = findViewById(R.id.btnShowList);
        insertBtn = findViewById(R.id.btnInsertItem);
        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLoca);;
        etPrice = findViewById(R.id.etPrice);
        ratingBar = findViewById(R.id.ratingbarStars);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String location = etLocation.getText().toString().trim();
                if (name.length() == 0 || location.length() == 0){
                    Toast.makeText(FourthActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }


                String price_str = etPrice.getText().toString().trim();
                int price = 0;
                try {
                    price = Integer.valueOf(price_str);
                } catch (Exception e){
                    Toast.makeText(FourthActivity.this, "Invalid price amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(FourthActivity.this);

                int rating = (int) ratingBar.getRating();
                dbh.insertItem(name, location, price, rating);
                dbh.close();
                Toast.makeText(FourthActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                etName.setText("");
                etLocation.setText("");
                etPrice.setText("");

            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(FourthActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}