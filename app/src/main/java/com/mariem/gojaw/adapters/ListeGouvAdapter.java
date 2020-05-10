package com.mariem.gojaw.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Gouvernorat;
import com.mariem.gojaw.ui.Destinations_eventsActivity;

import java.util.List;

public class ListeGouvAdapter extends RecyclerView.Adapter<ListeGouvAdapter.myViewHolder> {

    Context context;
    private List<Gouvernorat> mData;
    private LayoutInflater mInflater;
    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";
    public ListeGouvAdapter(Context context, List<Gouvernorat> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_gouv, parent, false);
        return new  myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Gouvernorat gouv=mData.get(position);
        holder.txtGouv.setText(gouv.getGouv());
        Glide.with(context).load(gouv.getImage()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Destinations_eventsActivity.class);
                intent.putExtra(Constant.ARG_GOUV,gouv.getGouv());
                context.startActivity(intent);

                sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Gouv", gouv.getGouv());
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView txtGouv;
        ImageView img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGouv=itemView.findViewById(R.id.txt_gouv);
            img=itemView.findViewById(R.id.image_gouv);
        }
    }
    }
