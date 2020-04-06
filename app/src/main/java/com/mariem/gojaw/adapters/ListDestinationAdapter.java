package com.mariem.gojaw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.mariem.gojaw.DATA.Data;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Destination;

import java.util.ArrayList;

public class ListDestinationAdapter extends RecyclerView.Adapter<ListDestinationAdapter.myViewHolder> {

    private ArrayList<Destination> mData;
    private Context mContext;
    private LayoutInflater mInflater;


    public ListDestinationAdapter(Context mContext, ArrayList<Destination> mData) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_destination, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {


        holder.txtLibelle.setText(mData.get(position).getLibelle());
        holder.txtCategorie.setText("Mode : "+mData.get(position).getCategorie());
        holder.txtDistance.setText(mData.get(position).getDistance());

        Glide.with(mContext).load(mData.get(position).getImage()).into(holder.imageView);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Destination currentDestination=mData.get(position);
                if (holder.checkBox.isChecked()){

                    if (Data.getInstance().getmDataSelected().size()<2){
                        currentDestination.setSelected(true);
                        Data.getInstance().getmDataSelected().add(currentDestination);
                        Snackbar.make(holder.checkBox,
                                currentDestination.getLibelle()+"a été choisi ", Snackbar.LENGTH_SHORT).show();

                    }else{
                        holder.checkBox.setChecked(false);
                        Snackbar.make(holder.checkBox,"2 distination si possible " ,Snackbar.LENGTH_SHORT).show();
                    }


                }else if(!holder.checkBox.isChecked()){
                    currentDestination.setSelected(false);
                    Data.getInstance().getmDataSelected().remove(currentDestination);
                    Snackbar.make(holder.checkBox,
                            currentDestination.getLibelle()+"a été supprimée ", Snackbar.LENGTH_SHORT).show();


                }
            }
        });

    }

    @Override
    public int getItemCount() {
      return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{
      ImageView imageView;
      TextView txtLibelle,txtCategorie,txtDistance;
      CheckBox checkBox;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLibelle=itemView.findViewById(R.id.tv_libelle);
            txtCategorie=itemView.findViewById(R.id.tv_categorie);
            txtDistance=itemView.findViewById(R.id.tv_km);
            imageView=itemView.findViewById(R.id.img_destination);
            checkBox=itemView.findViewById(R.id.checkbox);

        }
    }
}
