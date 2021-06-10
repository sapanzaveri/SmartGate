package com.example.smartgate.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchEventResponse {
    @SerializedName("Event")
    List<EventModel> eventModelList;

    public FetchEventResponse(List<EventModel> eventModelList) {
        this.eventModelList = eventModelList;
    }

    public List<EventModel> getEventModelList() {
        return eventModelList;
    }

    public void setEventModelList(List<EventModel> eventModelList) {
        this.eventModelList = eventModelList;
    }
}
