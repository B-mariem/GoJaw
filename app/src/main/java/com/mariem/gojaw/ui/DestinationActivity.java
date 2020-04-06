package com.mariem.gojaw.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.R;
import com.mariem.gojaw.DATA.RetrofitInterface;

import com.mariem.gojaw.adapters.ListDestinationAdapter;
import com.mariem.gojaw.models.Destination;
import com.mariem.gojaw.models.Position;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class DestinationActivity extends AppCompatActivity {



    private static  final int REQUEST_LOCATION=1;

    LocationManager locationManager;
    String latitude,longitude;
    Destination destination;

    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String Gouv;
    FloatingActionButton fab;



    RecyclerView myRecyclerView;
    ListDestinationAdapter destinationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);


        Intent intent = getIntent();

       addPermission();
        Gouv = intent.getStringExtra(Constant.ARG_GOUV);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Data.getInstance().getmDataSelected().size() == 0) {
                    Snackbar.make(fab,
                            "verifier ", Snackbar.LENGTH_LONG).show();
                } else {
                  startActivity(new Intent(getApplicationContext(), CreateEventActivity.class));
                }
            }
        });
        fetchDestination();
       // Toast.makeText(getApplicationContext(),longitude+latitude,Toast.LENGTH_LONG).show();
    }



    private void fetchDestination() {

        final ArrayList<Destination> destinationArrayList=new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.ARG_GOUV, Gouv);

        Call<List<Destination>> call = retrofitInterface.getByGOUv(map);
        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {

                if (response.code() == 200) {
                    for (int i = 0; i < response.body().size(); i++) {
                        destination = response.body().get(i);
                        //addPermission();

                            Position position = destination.getPosition();
                            float distance =
                                    calculateDistance(Double.parseDouble(position.getLatitude()), Double.parseDouble(position.getLongitude()),
                                            Double.parseDouble(latitude), Double.parseDouble(longitude));
                            destination.setDistance(distanceText(distance));

                           // destination.setTemps_arrive(0);

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

        destinationAdapter = new ListDestinationAdapter(this, destination);
        myRecyclerView.setAdapter(destinationAdapter);

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

        if (ActivityCompat.checkSelfPermission(DestinationActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(DestinationActivity.this,

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
//    private String getDirections(){
//        String latitudeDestination="37.192531";
//        String longitudeDestination="10.198560";
//
//        StringBuilder googleUrl=new StringBuilder("http://maps.googleapis.com/maps/api/directions/JSON?");
//        googleUrl.append("origin="+latitude+","+longitude);
//        googleUrl.append("&destination="+latitudeDestination+","+longitudeDestination);
//        googleUrl.append("&key="+R.string.google_maps_api_key);
//        return googleUrl.toString();
//    }

}

