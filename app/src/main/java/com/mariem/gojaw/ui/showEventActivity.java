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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.RetrofitInterface;
import com.mariem.gojaw.R;
import com.mariem.gojaw.adapters.ParamsAdapter;
import com.mariem.gojaw.models.Event;
import com.mariem.gojaw.models.User;
import com.mariem.gojaw.models.params;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showEventActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
TextView txtTitre,txtDATE,txtUserProfil,txtNbParticipants;
Button btnAction;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    RecyclerView rvParamsView;
    ParamsAdapter paramsAdapter;


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
        setContentView(R.layout.activity_show_event);
        initUI();
       setDrawerLayout();

        retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



         getEvent();


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getTypeEvent()){
                    case Constant.MES_EVENT_PARTICIPATED:
                        desabonneEvent(getIdEvent(),getUserId());

                        break;
                    case Constant.PARTICIPATE_EVENT:
                        participeToEvent(getIdEvent(),getUserId());

                        break;
                    case Constant.MES_EVENT_PRIVATE:
                        deleteEvent(getIdEvent());
                        break;

                    case Constant.MES_EVENT_PUBLIC:
                        deleteEvent(getIdEvent());
                        break;
                    default:break;
                }



            }
        });






    }
    private String getUserId() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString("ID_USER", null);
    }
    private String getIdEvent() {
        Intent intent=getIntent();
        return  intent.getStringExtra(Constant.ARG_ID_EVENT);
    }
    private String getTypeEvent() {
        Intent intent=getIntent();
        return  intent.getStringExtra(Constant.ARG_TYPE_EVENT);
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

    private void initUI() {
        txtTitre =findViewById(R.id.event_titre);
        rvParamsView=findViewById(R.id.rv_view_params);
        txtDATE=findViewById(R.id.event_date);
        btnAction =findViewById(R.id.btn_action);
        txtUserProfil=findViewById(R.id.txt_user_name);
        txtNbParticipants=findViewById(R.id.txt_nb_participants);

        btnAction.setText(getTypeEvent());


        if(getTypeEvent().equals(Constant.MES_EVENT_PRIVATE)){
            txtNbParticipants.setVisibility(View.INVISIBLE);
        }


    }
    private void deleteEvent(String idEvent){
        HashMap<String,String> map=new HashMap<>();
        map.put("id",idEvent);
        Call <Void> call=retrofitInterface.deleteEvent(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code()==200){
                    Snackbar.make(btnAction,
                            "success" , Snackbar.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                            finishAffinity();

                        }
                    },1000);

                }









            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Snackbar.make(btnAction,
                        "desole " , Snackbar.LENGTH_SHORT).show();


            }
        });

    }

    private void participeToEvent(String idEvent, String id_user) {
        HashMap<String,String> map=new HashMap<>();
        map.put("idEvent",idEvent);
        map.put("idParticipant",id_user);
        Call<Void> call=retrofitInterface.particpateToEvent(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code()==200){
                    Snackbar.make(btnAction,
                            "success" , Snackbar.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(getApplicationContext(),ListEventsActivity.class);
                            intent.putExtra(Constant.ARG_TYPE_EVENT,Constant.MES_EVENT_PARTICIPATED);
                            startActivity(intent);
                            finishAffinity();

                        }
                    },1000);

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    private void desabonneEvent(String idEvent, String id_user) {
        HashMap<String,String> map=new HashMap<>();
        map.put("idEvent",idEvent);
        map.put("idParticipant",id_user);
        Call<Void> call=retrofitInterface.desabonneEvent(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.code()==200){
                    Snackbar.make(btnAction,
                            "success" , Snackbar.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(getApplicationContext(),ListEventsActivity.class);
                            intent.putExtra(Constant.ARG_TYPE_EVENT,getTypeEvent());
                            startActivity(intent);
                            finishAffinity();

                        }
                    },1000);

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }
    private void getEvent() {

        HashMap<String,String>map=new HashMap<>();
        map.put("id",getIdEvent());
        Call <Event> call=retrofitInterface.getEventsByID(map);
        call.enqueue(new Callback <Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
             Event   myEvent=response.body();
                  txtTitre.setText(myEvent.toString());
                txtTitre.setText( myEvent.getTitre());
                txtDATE.setText( myEvent.getDate());

                txtNbParticipants.setText("N° participants : "+myEvent.getParticipants().size());

                ArrayList<params> params= myEvent.getParams();
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

                rvParamsView.setLayoutManager(manager);

                paramsAdapter= new ParamsAdapter(getApplicationContext(), params);
                rvParamsView.setAdapter(paramsAdapter);

                getUserProfil(myEvent.getId_user());


                  
                
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });


    }

    private void showUserProfil(final String userName, final String userProfil) {
        if((getTypeEvent().equals(Constant.PARTICIPATE_EVENT)) || (getTypeEvent().equals(Constant.MES_EVENT_PARTICIPATED))  ){
            txtUserProfil.setVisibility(View.VISIBLE);
            txtUserProfil.setText("Organiser par : "+userName);
            txtUserProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(userProfil));
                    startActivity(i);
                    //finishAffinity();
                }
            });

        }
    }

    private void getUserProfil(String id_user) {
        HashMap<String,String> map=new HashMap<>();
        map.put("idUser",id_user);

        Call <User> call=retrofitInterface.getUserProfil(map);
        call.enqueue(new Callback <User>() {
            @Override
            public void onResponse(Call <User> call, Response <User> response) {
                User user=response.body();


               showUserProfil(user.getName(),user.getUserURL());

            }

            @Override
            public void onFailure(Call <User> call, Throwable t) {
              //  Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
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
