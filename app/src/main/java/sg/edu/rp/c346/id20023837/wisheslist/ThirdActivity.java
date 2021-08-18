package sg.edu.rp.c346.id20023837.wisheslist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etPrice, etLoca;
    Button cancelBtn, updateBtn, btnDelete;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        cancelBtn = (Button)findViewById(R.id.btnCancel);
        updateBtn = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etLoca = (EditText) findViewById(R.id.etLocation);
        ratingBar = findViewById(R.id.ratingbarStars);

        Intent i = getIntent();
        final item currentItem = (item) i.getSerializableExtra("item");

        etID.setText(currentItem.getId()+"");
        etTitle.setText(currentItem.getName());
        etLoca.setText(currentItem.getLocation());
        etPrice.setText(currentItem.getPrice()+"");

        ratingBar.setRating(currentItem.getStars());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentItem.setName(etTitle.getText().toString().trim());
                currentItem.setLocation(etLoca.getText().toString().trim());
                int price = 0;
                try {
                    price = Integer.valueOf(etPrice.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid price amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentItem.setPrice(price);

                currentItem.setStars((int) ratingBar.getRating());
                int result = dbh.updateItem(currentItem);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Item updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this item? \n" + currentItem.getName());
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteItem(currentItem.getId());
                        if (result > 0) {
                            Toast.makeText(ThirdActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}
