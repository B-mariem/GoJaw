package com.mariem.gojaw;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/signin" )
    Call<LoginResult> executeLogin(@Body HashMap<String,String> map);
    @POST("/user/register-user")
    Call<Void> executeSignup(@Body HashMap<String,String> map);
}
