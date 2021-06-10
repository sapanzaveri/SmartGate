package com.example.smartgate.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchComplaintResponse {
    @SerializedName("Complaint")
    List<ComplaintModel> complaintModelList;

    public FetchComplaintResponse(List<ComplaintModel> complaintModelList) {
        this.complaintModelList = complaintModelList;
    }

    public List<ComplaintModel> getComplaintModelList() {
        return complaintModelList;
    }

    public void setComplaintModelList(List<ComplaintModel> complaintModelList) {
        this.complaintModelList = complaintModelList;
    }
}
