package com.example.smartgate.Api;

import com.example.smartgate.Model.FetchComplaintResponse;
import com.example.smartgate.Model.FetchEventResponse;
import com.example.smartgate.Model.FetchNoticeResponse;
import com.example.smartgate.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("Dasboard.php")
    Call<FetchNoticeResponse> getAllNotice();

    @GET("Dasboard.php")
    Call<FetchEventResponse> getAllEvent();

    @GET("Dasboard.php")
    Call<FetchComplaintResponse> getAllComplaints();

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(
            @Field("User_Name") String User_Name,
            @Field("Password") String Password,
            @Field("Flag") String Flag
    );



}
