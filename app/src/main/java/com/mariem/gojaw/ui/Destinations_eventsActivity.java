package com.mariem.gojaw.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.R;
import com.mariem.gojaw.DATA.RetrofitInterface;

import com.mariem.gojaw.adapters.ListDestinationAdapter;
import com.mariem.gojaw.adapters.ListEventAdapter;
import com.mariem.gojaw.models.Destination;
import com.mariem.gojaw.models.Event;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Destinations_eventsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static  final int REQUEST_LOCATION=1;
    LocationManager locationManager;
    String latitude,longitude;
    Destination destination;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String Gouv;

    FloatingActionButton fab;
    TextView txtEvent,txtDest;

    RecyclerView myRecyclerView,rvListEvent;
    ListDestinationAdapter destinationAdapter;
    ListEventAdapter eventUserAdapter;
    LinearLayoutManager HorizontalLayout;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);
        setDrawerBar();
        initUI();
        addPermission();
        Data.getInstance().getmDataSelected().clear();

        Intent intent = getIntent();
        Gouv = intent.getStringExtra(Constant.ARG_GOUV);
        txtEvent.setText("les événements recommandés");
        txtDest.setText("Créé votre événement");

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Data.getInstance().getmDataSelected().size() == 0) {
                    Snackbar.make(fab,
                            "verifier ", Snackbar.LENGTH_LONG).show();
                } else {
                  startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
                  finishAffinity();
                }
            }
        });

        fetchDestination();

        fetchEvents();




    }

    @SuppressLint("RestrictedApi")
    private void setDrawerBar() {
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

    private void initUI() {

        fab = findViewById(R.id.fab);
        txtEvent=findViewById(R.id.txt);
        txtDest=findViewById(R.id.txtdest);

    }

    private void fetchEvents() {
        HashMap<String,String> map=new HashMap<>();
        map.put(Constant.ARG_GOUV,Gouv);
        map.put("idUser",sharedpreferences.getString("ID_USER", null));
        Call <List<Event>> call =retrofitInterface.getEventsByGOUv(map);
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                showEvents(response.body());

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }


    private void fetchDestination() {

        final ArrayList<Destination> destinationArrayList=new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.ARG_GOUV, Gouv);

        Call<List<Destination>> call = retrofitInterface.getDestinationsByGOUv(map);
        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {

                if (response.code() == 200) {
                    for (int i = 0; i < response.body().size(); i++) {
                        destination = response.body().get(i);
                            float distance =
                                    calculateDistance(Double.parseDouble(destination.getLatitude()), Double.parseDouble(destination.getLongitude()),
                                            Double.parseDouble(latitude), Double.parseDouble(longitude));
                            destination.setDistance(distanceText(distance));


                        destinationArrayList.add(destination);
                        showData(destinationArrayList);
                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), "en cours de traitement", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {

            }
        });


    }

    private void showData(ArrayList<Destination> destination) {
        myRecyclerView = findViewById(R.id.rv_destination);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(manager);
        Collections.sort(destination,Destination.sortByDes);

        destinationAdapter = new ListDestinationAdapter(this, destination,true);
        myRecyclerView.setAdapter(destinationAdapter);

    }
    public void showEvents(List<Event> body){
        rvListEvent = findViewById(R.id.rv_events);

       // RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        HorizontalLayout
                = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

        rvListEvent.setLayoutManager(HorizontalLayout);
        Collections.sort(body,Event.sortByDate);

        eventUserAdapter= new ListEventAdapter(this,body,Constant.PARTICIPATE_EVENT);
        rvListEvent.setAdapter(eventUserAdapter);
    }


    private String distanceText(Float distance) {
        String distanceString;

        if (distance < 1000)
            if (distance < 1)
                distanceString = String.format(Locale.US, "%dm", 1);
            else
                distanceString = String.format(Locale.US, "%dm", Math.round(distance));
        else if (distance > 10000)
            if (distance < 1000000)
                distanceString = String.format(Locale.US, "%dkm", Math.round(distance / 1000));
            else
                distanceString = "FAR";
        else
            distanceString = String.format(Locale.US, "%.2fkm", distance / 1000);
        return distanceString;


    }

    private float calculateDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
        float[] results = new float[1];
        Location.distanceBetween(lat1, lng1, lat2, lng2, results);
        return results[0];

    }

    private void addPermission(){
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    getLocation();
                }

    }
    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(Destinations_eventsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(Destinations_eventsActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

               Toast.makeText(getApplicationContext(),
                       "Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude
                                                    ,Toast.LENGTH_SHORT).show();
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Toast.makeText(getApplicationContext(),
                        "Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude
                        ,Toast.LENGTH_SHORT).show();
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                Toast.makeText(getApplicationContext(),
                        "Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude
                        ,Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



}



