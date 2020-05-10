package com.mariem.gojaw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.params;

import java.util.ArrayList;

public class ParamsAdapter extends RecyclerView.Adapter<ParamsAdapter.myViewHolder>{
    private Context mContext;
    private ArrayList<params> paramsArrayList;
    private LayoutInflater mInflater;

    public ParamsAdapter(Context mContext, ArrayList<params> paramsArrayList) {
        this.mContext = mContext;
        this.paramsArrayList = paramsArrayList;
        this.mInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_view_params, parent, false);
        return new ParamsAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        params params=paramsArrayList.get(position);
        holder.textViewTime.setText(params.getTempsArrive());
        holder.textViewLibelle.setText(params.getDestination());
        Glide.with(mContext).load(params.getImage()).into(holder.imgParams);


    }

    @Override
    public int getItemCount() {
        return paramsArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView textViewLibelle,textViewTime;
        ImageView imgParams;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLibelle=itemView.findViewById(R.id.text_libelle);
            textViewTime=itemView.findViewById(R.id.text_time);
            imgParams=itemView.findViewById(R.id.img_params);
        }
    }

}
