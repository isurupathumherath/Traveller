package com.example.madfinaltraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class currency_change extends AppCompatActivity {

    Spinner sp1, sp2;
    EditText ed1;
    Button b1;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_change);

        ed1 = findViewById(R.id.txtamount);
        sp1 = findViewById(R.id.spfrom );
        sp2 = findViewById(R.id.spto );
        b1 = findViewById(R.id.btn1);

        Intent intent = getIntent();
        str = intent.getStringExtra("currency");
        ed1.setText(str);

        String[] from = {"Sri Lankan Rupees"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);

        String[] to = {"Indian Rupees", "USD"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Double tot;
                Double amount = Double.parseDouble(ed1.getText().toString());

                if(sp1.getSelectedItem().toString() == "Sri Lankan Rupees" && sp2.getSelectedItem().toString() == "Indian Rupees"){
                    tot = amount * 0.37;
                    Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
                }
                else if(sp1.getSelectedItem().toString() == "Sri Lankan Rupees" && sp2.getSelectedItem().toString() == "USD"){
                    tot = amount * 0.0050;
                    Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}