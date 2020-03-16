package com.mariem.gojaw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Ville;

import java.util.List;

public class VillesAdapter extends RecyclerView.Adapter<VillesAdapter.ViewHolder> {

    Context context;
    private List<Ville> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public VillesAdapter(Context context, List<Ville> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ville, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Ville ville = mData.get(position);

        holder.tvVille.setText(ville.getVille());
        Glide.with(context).load(ville.getImage()).into(holder.imgPicture);

    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvVille;
        ImageView imgPicture;


        ViewHolder(View itemView) {
            super(itemView);
            tvVille = itemView.findViewById(R.id.tv_ville);
            imgPicture = itemView.findViewById(R.id.img_picture);

        }


    }
}
