package com.mariem.gojaw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView posBizerte, posTozeur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(), "BienVenue" + sharedpreferences.getString("ID_USER", null)
                , Toast.LENGTH_LONG).show();
        posBizerte = findViewById(R.id.pos_bizerte);
        posTozeur = findViewById(R.id.pos_Tozeur);
       /*posBizerte.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Intent intent= new Intent(getApplicationContext(),VilleActivity.class);
              intent.putExtra("GOUV","bizerte");
              startActivity(intent);
           }
       });
*/
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profile:
                Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.event:
                Toast.makeText(getApplicationContext(), "event    us Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean("IS_CONNECTED", false);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finishAffinity();
                break;
            default:
                break;
        }
        return false;
    }

    public void click(View view) {
        Intent intent = new Intent(getApplicationContext(), VilleActivity.class);
        switch (view.getId()) {
            case R.id.pos_bizerte:
                intent.putExtra("GOUV", "bizerte");
                startActivity(intent);
                break;
            case R.id.pos_Tozeur:
                intent.putExtra("GOUV", "tozeur");
                startActivity(intent);
        }
    }
}