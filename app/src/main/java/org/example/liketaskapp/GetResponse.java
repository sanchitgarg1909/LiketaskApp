package org.example.liketaskapp;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetResponse {
    @FormUrlEncoded
    @POST("/api/v2/users/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);
}
