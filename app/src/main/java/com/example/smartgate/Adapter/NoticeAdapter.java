package com.example.smartgate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgate.Model.FetchNoticeResponse;
import com.example.smartgate.Model.NoticeModel;
import com.example.smartgate.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private Context context;
    private List<NoticeModel> noticeModelList;

    public NoticeAdapter(Context context, List<NoticeModel> noticeModelList) {
        this.context = context;
        this.noticeModelList = noticeModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_card_view, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position) {

        holder.txt_NoticeTitle.setText(noticeModelList.get(position).getTitle());
        holder.txt_NoticeDescription.setText(noticeModelList.get(position).getDescription());
        holder.txt_NoticeDate.setText(noticeModelList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return noticeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_NoticeTitle;
        private TextView txt_NoticeDescription;
        private TextView txt_NoticeDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_NoticeTitle = itemView.findViewById(R.id.txt_NoticeTitle);
            txt_NoticeDescription = itemView.findViewById(R.id.txt_NoticeDescription);
            txt_NoticeDate = itemView.findViewById(R.id.txt_NoticeDate);

        }
    }

}
