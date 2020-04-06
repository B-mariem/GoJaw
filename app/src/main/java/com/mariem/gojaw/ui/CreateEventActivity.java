package com.mariem.gojaw.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.DATA.RetrofitInterface;
import com.mariem.gojaw.R;
import com.mariem.gojaw.adapters.CreateEventAdapter;
import com.mariem.gojaw.adapters.ListDestinationAdapter;
import com.mariem.gojaw.models.Event;

import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener{
TextView txtDateEvent,txt;
EditText edtTitreEvent;
Button btnDateEvent;
int MM,yyyy,dd,hh,mm;
int  MM_x,yyyy_x,dd_x,hh_x,mm_x;
    RecyclerView myRecyclerView;
    CreateEventAdapter eventDesAdapter;
    Button btnSend;
    String type;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);
        btnDateEvent =findViewById(R.id.btn_date_event);
        txtDateEvent =findViewById(R.id.txt_date_event);
        edtTitreEvent =findViewById(R.id.edt_name);
        btnSend=findViewById(R.id.btn_send_event);
        retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Spinner aSpinner = findViewById(R.id.aSpinner);
        aSpinner.setOnItemSelectedListener(this);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           if(verifTime()){
               if (verifDate() || verifEdtTitre()){
                   Event event=new Event();
                   event.setId_user(sharedpreferences.getString("ID_USER", null));
                   event.setTitre(edtTitreEvent.getText().toString());
                   event.setDate(txtDateEvent.getText().toString());
                   event.setDestinations(Data.getInstance().getmDataSelected());
                   event.setType(type);

                   HashMap<String,String> map=new HashMap<>();
                   map.put("id_user",event.getId_user());
                   map.put("titre",event.getTitre());
                   map.put("date",event.getDate());
                   map.put("destinations",event.getDestinations().toString());
                   map.put("type",event.getType());
                   Call<Void> call=retrofitInterface.createEvent(map);
                   call.enqueue(new Callback<Void>() {
                       @Override
                       public void onResponse(Call<Void> call, Response<Void> response) {
                           if(response.code()==200){
                               Snackbar.make(btnSend,
                                       "success" , Snackbar.LENGTH_SHORT).show();
                               Data.getInstance().getmDataSelected().clear();
                               Handler handler = new Handler();
                               handler.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                                                       }
                                   },1000);

                           }
                       }

                       @Override
                       public void onFailure(Call<Void> call, Throwable t) {
                           Snackbar.make(btnSend,
                                   t.getMessage(), Snackbar.LENGTH_LONG).show();


                       }
                   });

               }

           }


            // Toast.makeText(getApplicationContext(),
                // verifTime()+"",Toast.LENGTH_LONG).show();



            }

        });
        //date event
        txt=findViewById(R.id.txt);
        btnDateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                yyyy=c.get(Calendar.YEAR);
                MM=c.get(Calendar.MONTH);
                dd=(c.get(Calendar.DAY_OF_MONTH));
                DatePickerDialog datePickerDialog=new DatePickerDialog(CreateEventActivity.this,
                        CreateEventActivity.this,yyyy,MM,dd);
                datePickerDialog.show();
            }
        });



        myRecyclerView = findViewById(R.id.rv_event_des);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(manager);

        eventDesAdapter = new CreateEventAdapter(this, Data.getInstance().getmDataSelected());
        myRecyclerView.setAdapter(eventDesAdapter);

    }

    private boolean verifEdtTitre(){
        if (edtTitreEvent.getText().toString()!=null){
            return true;
        }else return false;

    }
    private boolean verifDate(){
        if(txtDateEvent.getText().toString()!=null){
            return true;
        }else return false;
    }
    private boolean verifTime(){
        int diffheure = Data.getInstance().getmDataSelected().get(1).getHeure_arrive_des() - Data.getInstance().getmDataSelected().get(0).getHeure_arrive_des();
        if (diffheure > 2) {
            return true;
        } else {

            return false;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yyyy_x=year;
        MM_x=month+1;
        dd_x=dayOfMonth;
        if(dayOfMonth>dd){
            String res= dd_x+"/"+MM_x+"/"+yyyy;
            txtDateEvent.setText(res);
        }else {
            Toast.makeText(getApplicationContext(),"erreur",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        type=adapterView.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        hh_x=hourOfDay;
//        mm_x=minute;
//        String res =hh_x+"h"+mm_x;
//        txtTimeDepart.setText(res);
//
//
//    }
    //time event
//txtTimeDepart.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Calendar c=Calendar.getInstance();
//            hh=c.get(Calendar.HOUR);
//            mm=c.get(Calendar.MINUTE);
//            TimePickerDialog TimePickerDialog=new TimePickerDialog(CreateEventActivity.this,
//                    CreateEventActivity.this,hh,mm,true);
//            TimePickerDialog.show();
//        }
//    });
}
