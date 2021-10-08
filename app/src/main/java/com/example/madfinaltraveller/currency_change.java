package com.example.madfinaltraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class currency_change extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    Spinner sp1, sp2;
    EditText ed1;
    Button b1;
    String str;
    String usern;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
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

        Intent intent2=getIntent();
        usern=intent2.getStringExtra("un");


        /*nav*/
        drawerLayout=findViewById(R.id.drawer_layout6);
        navigationView=findViewById(R.id.nav_view6);
        toolbar=findViewById(R.id.toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        /*nav*/

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

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.homepage:
                Intent i9=new Intent(this,ClientHomenew.class);
                i9.putExtra("un",usern);
                startActivity(i9);
                break;
            case R.id.profile:
                break;
            case R.id.log_o:
                Intent i0=new Intent(this,Loginpage.class);
                i0.putExtra("un",usern);
                startActivity(i0);
                break;
            case R.id.viewHotel:
                Intent i = new Intent(this, activity_show_hotel.class);
                i.putExtra("un",usern);
                startActivity(i);
                break;
            case R.id.viewguide:
                Intent i1 = new Intent(this, MainActivity.class);
                i1.putExtra("un",usern);
                startActivity(i1);
                break;
            case R.id.viewtaxi:
                break;
        }
        return true;
    }
}