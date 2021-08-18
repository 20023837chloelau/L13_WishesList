package sg.edu.rp.c346.id20023837.wisheslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button submitBtn;
    TextView tvP1, tvP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitBtn = findViewById(R.id.submitBtn);
        tvP1 = findViewById(R.id.tvIntro1);
        tvP2 = findViewById(R.id.tvIntro2);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.Small) {
            tvP1.setTextSize(15);
            tvP2.setTextSize(15);
            return true;
        } else if (id == R.id.Normal) {
            tvP1.setTextSize(20);
            tvP2.setTextSize(20);
            return true;
        } else if (id == R.id.Large) {
            tvP1.setTextSize(25);
            tvP2.setTextSize(25);
        } else {
            tvP1.setTextSize(15);
            tvP2.setTextSize(15);
        }

        return super.onOptionsItemSelected(item);
    }
}