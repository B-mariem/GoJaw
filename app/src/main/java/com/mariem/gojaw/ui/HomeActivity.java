package com.mariem.gojaw.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.DATA.RetrofitInterface;
import com.mariem.gojaw.R;
import com.mariem.gojaw.adapters.ListeGouvAdapter;
import com.mariem.gojaw.models.Gouvernorat;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//implements NavigationView.OnNavigationItemSelectedListener
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    RecyclerView rvGouv;
    private ListeGouvAdapter listeGouvAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Data.getInstance().getmDataSelected().clear();


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

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("Gouv", "");
        editor.apply();
        getAllGouv();



    }

    private void getAllGouv() {

        Call<List<Gouvernorat>> call=retrofitInterface.getAllGouv();
        call.enqueue(new Callback<List<Gouvernorat>>() {
            @Override
            public void onResponse(Call<List<Gouvernorat>> call, Response<List<Gouvernorat>> response) {
                showData(response.body());


            }

            @Override
            public void onFailure(Call<List<Gouvernorat>> call, Throwable t) {

            }
        });
    }

    private void showData(List<Gouvernorat> body) {
        rvGouv = findViewById(R.id.rv_list_gouv);
       // RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
       GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rvGouv.setLayoutManager(gridLayoutManager);
        Collections.sort(body,Gouvernorat.sortByGouv);

       listeGouvAdapter= new ListeGouvAdapter(this, body);
        rvGouv.setAdapter(listeGouvAdapter);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent=new Intent(getApplicationContext(), ListEventsActivity.class);
        switch (menuItem.getItemId()) {
            case R.id.home:
                Toast.makeText(getApplicationContext(), "Home Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.event:
                intent.putExtra(Constant.ARG_TYPE_EVENT,Constant.MES_EVENT_PUBLIC);
                startActivity(intent);
                finishAffinity();
                break;
            case R.id.participated_events:
                intent.putExtra(Constant.ARG_TYPE_EVENT,Constant.MES_EVENT_PARTICIPATED);
                startActivity(intent);
                finishAffinity();
                break;
            case R.id.private_event:
                intent.putExtra(Constant.ARG_TYPE_EVENT,Constant.MES_EVENT_PRIVATE);
                startActivity(intent);
                finishAffinity();
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



}