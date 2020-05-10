package com.mariem.gojaw.adapters;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Destination;
import com.mariem.gojaw.models.params;
import com.mariem.gojaw.ui.CreateEventActivity;

import java.util.ArrayList;

import static java.lang.String.valueOf;

//implements TimePickerDialog.OnTimeSetListener
public  class CreateEventAdapter extends RecyclerView.Adapter<CreateEventAdapter.myViewHolder>{
    private ArrayList<Destination> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    private ArrayAdapter<String> hoursAdapter;
    private ArrayAdapter<String> minuteAdapter;


    public CreateEventAdapter(Context mContext, ArrayList<Destination> mData) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

        hoursAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        minuteAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item);
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_create_event, parent, false);

        return new CreateEventAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        final Destination currentDestination =mData.get(position);
        final String[] hour = new String[1];
        final String[] minute = new String[1];
        final params params=new params();





        holder.spinnerHour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hour[0] = parent.getItemAtPosition(position).toString();
                holder.spinnerMinute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        minute[0] = parent.getItemAtPosition(position).toString();

                        params.setTempsArrive(hour[0]+":"+minute[0]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        holder.txtLibelle.setText(currentDestination.getLibelle());

        params.setDestination(currentDestination.getLibelle());
        params.setImage(currentDestination.getImage());
        Data.getInstance().getmDataparams().add(params);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public class myViewHolder extends RecyclerView.ViewHolder{

      TextView txtLibelle;
      Spinner spinnerHour,spinnerMinute;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLibelle=itemView.findViewById(R.id.txt_des_libelle);

            spinnerHour=itemView.findViewById(R.id.spinner_hour);
            spinnerMinute=itemView.findViewById(R.id.spinner_minute);




        }


    }


}
