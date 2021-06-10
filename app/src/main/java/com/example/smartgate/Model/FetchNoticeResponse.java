package com.example.smartgate.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchNoticeResponse {
    @SerializedName("News")
    List<NoticeModel> noticeModelList;

    public FetchNoticeResponse(List<NoticeModel> noticeModelList) {
        this.noticeModelList = noticeModelList;
    }

    public List<NoticeModel> getNoticeModelList() {
        return noticeModelList;
    }

    public void setNoticeModelList(List<NoticeModel> noticeModelList) {
        this.noticeModelList = noticeModelList;
    }
}
