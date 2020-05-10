package com.mariem.gojaw.ui;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.RetrofitInterface;
import com.mariem.gojaw.R;
import com.mariem.gojaw.adapters.ListEventAdapter;
import com.mariem.gojaw.models.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListEventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    RecyclerView rvListEvent;
    ListEventAdapter eventUserAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);
        setDrawerLayout();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        showEvents();






    }

    private void showEvents() {
        Intent intent=getIntent();
        String type=intent.getStringExtra(Constant.ARG_TYPE_EVENT);

        switch (type){
            case Constant.MES_EVENT_PARTICIPATED:getMyParticipatedEvents();break;
            case Constant.MES_EVENT_PRIVATE: getMyPraviteEvents();break;
            case Constant.MES_EVENT_PUBLIC : getMyPublicEvents();break;
            default:break;
        }
    }

    private String getUserId() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       return sharedpreferences.getString("ID_USER", null);
    }

    @SuppressLint("RestrictedApi")
    private void setDrawerLayout() {

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

    public  void getMyPublicEvents() {


        HashMap<String,String> map=new HashMap<>();
        map.put("id_user",getUserId());

        Call<List<Event>> call=retrofitInterface.myEvents(map);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
               showData(response.body(),Constant.MES_EVENT_PUBLIC);


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public  void getMyParticipatedEvents() {
        HashMap<String,String> map=new HashMap<>();
        map.put("idUser",getUserId());

        Call<List<Event>> call=retrofitInterface.getParticipatedEvents(map);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                showData(response.body(),Constant.MES_EVENT_PARTICIPATED);


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
    public  void getMyPraviteEvents() {


        HashMap<String,String> map=new HashMap<>();
        map.put("idUser",getUserId());

        Call<List<Event>> call=retrofitInterface.getPrivateEvents(map);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                showData(response.body(),Constant.MES_EVENT_PRIVATE);


            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }
    private void showData(List<Event> body,String type) {
       rvListEvent = findViewById(R.id.rv_list_event_user);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        rvListEvent.setLayoutManager(manager);
        Collections.sort(body,Event.sortByDate);

        eventUserAdapter= new ListEventAdapter(this, body,type);
        rvListEvent.setAdapter(eventUserAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent=new Intent(getApplicationContext(), ListEventsActivity.class);
        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent i =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                finish();
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