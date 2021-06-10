package com.example.smartgate.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgate.Adapter.ComplaintAdapter;
import com.example.smartgate.Adapter.EventAdapter;
import com.example.smartgate.Adapter.NoticeAdapter;
import com.example.smartgate.MainActivity;
import com.example.smartgate.Model.ComplaintModel;
import com.example.smartgate.Model.EventModel;
import com.example.smartgate.Model.FetchComplaintResponse;
import com.example.smartgate.Model.FetchEventResponse;
import com.example.smartgate.Model.NoticeModel;
import com.example.smartgate.RetrofitClient;
import com.example.smartgate.Model.FetchNoticeResponse;
import com.example.smartgate.R;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewNotice;
    RecyclerView recyclerViewEvent;
    RecyclerView recyclerViewComplaint;
    List<NoticeModel> noticeModelList;
    List<EventModel> eventModelList;
    List<ComplaintModel> complaintModelList;
    TextView txt_NoitceViewAll;
    TextView txt_EventViewAll;
    TextView txt_ComplaintViewAll;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewNotice = view.findViewById(R.id.rv_NoticeView);
        recyclerViewEvent = view.findViewById(R.id.rv_UpcomingEvent);
        recyclerViewComplaint = view.findViewById(R.id.rv_Complaints);
        txt_NoitceViewAll = view.findViewById(R.id.txt_NoticeView);
        txt_EventViewAll = view.findViewById(R.id.txt_UpcomingEventsView);
        txt_ComplaintViewAll = view.findViewById(R.id.txt_ComplaintsView);


        ClickEvents();
        GetNotice();
        GetEvents();
        GetCompalints();


    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Home");
        ((MainActivity) getActivity()).setNav("Home");

    }

    private void ClickEvents() {

        txt_NoitceViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setActionBarTitle("Notice");
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container,
                                new NoticeFragment()).commit();

                ((MainActivity) getActivity()).setNav("Notice");

            }
        });

        txt_EventViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setActionBarTitle("Event");
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)

                        .replace(R.id.fragment_container,
                                new EventFragment()).commit();
                ((MainActivity) getActivity()).setNav("Event");
            }
        });

        txt_ComplaintViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setActionBarTitle("Complaint");
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container,
                                new ComplaintFragment()).commit();
                ((MainActivity) getActivity()).setNav("Complaint");
            }
        });
    }

    private void GetCompalints() {
        recyclerViewComplaint.setHasFixedSize(true);
        recyclerViewComplaint.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        Call<FetchComplaintResponse> ccall = RetrofitClient.getInstance().getApi().getAllComplaints();
        ccall.enqueue(new Callback<FetchComplaintResponse>() {
            @Override
            public void onResponse(Call<FetchComplaintResponse> call, Response<FetchComplaintResponse> response) {
                if (response.isSuccessful()) {
                  
                    complaintModelList = response.body().getComplaintModelList();
                    recyclerViewComplaint.setAdapter(new ComplaintAdapter(getActivity(), complaintModelList));
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchComplaintResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void GetEvents() {
        recyclerViewEvent.setHasFixedSize(true);
        recyclerViewEvent.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        Call<FetchEventResponse> ecall = RetrofitClient.getInstance().getApi().getAllEvent();
        ecall.enqueue(new Callback<FetchEventResponse>() {
            @Override
            public void onResponse(Call<FetchEventResponse> call, Response<FetchEventResponse> response) {
                if (response.isSuccessful()) {
                    eventModelList = response.body().getEventModelList();
                    recyclerViewEvent.setAdapter(new EventAdapter(getActivity(), eventModelList));
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchEventResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetNotice() {
        recyclerViewNotice.setHasFixedSize(true);
        recyclerViewNotice.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        Call<FetchNoticeResponse> call = RetrofitClient.getInstance().getApi().getAllNotice();
        call.enqueue(new Callback<FetchNoticeResponse>() {
            @Override
            public void onResponse(Call<FetchNoticeResponse> call, Response<FetchNoticeResponse> response) {
                if (response.isSuccessful()) {
                    noticeModelList = response.body().getNoticeModelList();
                    recyclerViewNotice.setAdapter(new NoticeAdapter(getActivity(), noticeModelList));
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchNoticeResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
