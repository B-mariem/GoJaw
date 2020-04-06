package com.mariem.gojaw.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Destination;

import java.util.ArrayList;

import static java.lang.String.valueOf;

//implements TimePickerDialog.OnTimeSetListener
public  class CreateEventAdapter extends RecyclerView.Adapter<CreateEventAdapter.myViewHolder> {
    private ArrayList<Destination> mData;
    private Context mContext;
    private LayoutInflater mInflater;
    int heure;
    int minute;
//public Destination currentDestination = new Destination();


    public CreateEventAdapter(Context mContext, ArrayList<Destination> mData) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

        heure = 8;


        minute = 0;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_create_event, parent, false);

        return new CreateEventAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {



        String libelle=mData.get(position).getLibelle();
        holder.txtLibelle.setText(libelle);
        holder.txtNote.setText("au minimun 3h entre les destinations");
       final Destination currentDestination =mData.get(position);

    holder.edtHeure.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

           if(Integer.parseInt(holder.edtHeure.getText().toString())>23){

              holder.txtNote.setText("veuillez entrer un nombre entre 0 et 24");
              holder.edtHeure.setText("0");
              heure=0;
           }else{
               heure = Integer.parseInt(holder.edtHeure.getText().toString());
               currentDestination.setHeure_arrive_des(heure);
           }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
    //getMinute
    holder.edtMinute.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(Integer.parseInt(holder.edtMinute.getText().toString())>59){
                holder.txtNote.setText("veuillez entrer un nombre entre 0 et 60");
                holder.edtMinute.setText("0");
                minute=0;
            }else{
                minute = Integer.parseInt(holder.edtMinute.getText().toString());
                currentDestination.setMinute_arrive_des(minute);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }





    public class myViewHolder extends RecyclerView.ViewHolder{
EditText edtHeure,edtMinute;
      TextView txtLibelle, txtNote;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLibelle=itemView.findViewById(R.id.txt_des_libelle);
            txtNote =itemView.findViewById(R.id.txt_note);
            edtHeure=itemView.findViewById(R.id.edt_heure);
            edtMinute=itemView.findViewById(R.id.edt_minute);



        }


    }

}
