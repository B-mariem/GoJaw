package com.mariem.gojaw.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.R;
import com.mariem.gojaw.models.Event;
import com.mariem.gojaw.ui.showEventActivity;

import java.util.List;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.myViewHolder>{
    Context context;
    private List<Event> mData;
    private LayoutInflater mInflater;
    private String type;


    public ListEventAdapter(Context context, List<Event> mData,String type) {

        this.context = context;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        this.type=type;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_events, parent, false);
        return new  myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {

        final Event event=mData.get(position);
        holder.txtTitre.setText(event.getTitre());
        holder.txtDate.setText(event.getDate());

        switch (type){
            case Constant.MES_EVENT_PARTICIPATED:
                holder.txtCreatedBy.setText(" Organiser par :  "+event.getCreatedBy()+"\n"+"\n N° participants : "+event.getParticipants().size());
                break;
            case Constant.PARTICIPATE_EVENT:
                holder.txtCreatedBy.setText("Organiser par : "+event.getCreatedBy()+"\n "+" N° participants : "+event.getParticipants().size());
                break;
            case Constant.MES_EVENT_PRIVATE:
                holder.txtCreatedBy.setVisibility(View.INVISIBLE);
                break;
            case Constant.MES_EVENT_PUBLIC :
                holder.txtCreatedBy.setText("N° participants : "+event.getParticipants().size());
                break;
            default:break;
        }



        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, showEventActivity.class);
               intent.putExtra(Constant.ARG_ID_EVENT,event.get_id());
               intent.putExtra(Constant.ARG_TYPE_EVENT,type);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitre,txtDate,txtGouv,txtCreatedBy;
        Button btnPlus;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate=itemView.findViewById(R.id.txt_date);
            txtGouv=itemView.findViewById(R.id.txt_gouv);
            txtTitre=itemView.findViewById(R.id.txt_titre);
            btnPlus =itemView.findViewById(R.id.btn_plus);
            txtCreatedBy=itemView.findViewById(R.id.txt_createdBy);

        }
    }
}
