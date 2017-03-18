package com.wufang.trip.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface UserManageService {
    @GET("login" )
    Call<String> login(
            @Query("mobilePhoneNumber") String phone,
            @Query("password") String password
    );
    @GET("reg" )
    Call<String> reg(
            @Query("mobilePhoneNumber") String phone,
            @Query("password") String password
    );
}

