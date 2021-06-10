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
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.smartgate.Model.ComplaintModel;
import com.example.smartgate.Model.EventModel;
import com.example.smartgate.R;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private Context context;
    private List<ComplaintModel> complaintModelList;

    public ComplaintAdapter(Context context, List<ComplaintModel> complaintModelList) {
        this.context = context;
        this.complaintModelList = complaintModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_card_view, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {


        holder.txt_ComplaintTitle.setText(complaintModelList.get(position).getName());
        holder.txt_ComplaintDesc.append(complaintModelList.get(position).getDescription());
        if(complaintModelList.get(position).getIsActive().equals("1")){
            holder.txt_CompalaintStatus.append("Pending");
        }else{
            holder.txt_CompalaintStatus.append("Solved");
        }



    }

    @Override
    public int getItemCount() {
        return complaintModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_ComplaintTitle;
        private TextView txt_ComplaintDesc;
        private TextView txt_CompalaintStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ComplaintTitle = itemView.findViewById(R.id.txt_ComplaintTitle);
            txt_ComplaintDesc = itemView.findViewById(R.id.txt_ComplaintDesc);
            txt_CompalaintStatus = itemView.findViewById(R.id.txt_CompalintStatus);


        }
    }

}
