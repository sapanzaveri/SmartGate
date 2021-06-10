package com.example.smartgate.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.smartgate.Model.EventModel;
import com.example.smartgate.Model.NoticeModel;
import com.example.smartgate.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<EventModel> eventModelList;

    public EventAdapter(Context context, List<EventModel> eventModelList) {
        this.context = context;
        this.eventModelList = eventModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_view, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {


        holder.txt_EventTitle.setText(eventModelList.get(position).getTitle());
        holder.txt_EventBudget.setText("Budget:-"+eventModelList.get(position).getBudget());
        holder.txt_EventSDate.setText(eventModelList.get(position).getFromDate());

        Glide.with(context)
                .load(eventModelList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                }).into(holder.img_EventProfile);
    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_EventTitle;
        private TextView txt_EventBudget;
        private TextView txt_EventSDate;

        private ImageView img_EventProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_EventTitle = itemView.findViewById(R.id.txt_EventTitle);
            txt_EventBudget = itemView.findViewById(R.id.txt_EventBudget);
            txt_EventSDate = itemView.findViewById(R.id.txt_EventSDate);

            img_EventProfile = itemView.findViewById(R.id.img_eventImg);

        }
    }

}
