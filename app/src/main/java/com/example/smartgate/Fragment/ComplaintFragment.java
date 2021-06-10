package com.example.smartgate.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgate.Adapter.ComplaintAdapter;
import com.example.smartgate.Model.ComplaintModel;
import com.example.smartgate.Model.FetchComplaintResponse;
import com.example.smartgate.R;
import com.example.smartgate.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintFragment extends Fragment {
    RecyclerView recyclerViewComplaint;
    List<ComplaintModel> complaintModelList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complaint, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewComplaint = view.findViewById(R.id.rv_Complaints);
        GetCompalints();
    }

    private void GetCompalints() {
        recyclerViewComplaint.setHasFixedSize(true);
        recyclerViewComplaint.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        Call<FetchComplaintResponse> Ccall = RetrofitClient.getInstance().getApi().getAllComplaints();
        Ccall.enqueue(new Callback<FetchComplaintResponse>() {
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


}
