package com.mariem.gojaw;

import com.mariem.gojaw.models.Ville;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/signin" )
    Call<LoginResult> executeLogin(@Body HashMap<String,String> map);
    @POST("/user/register-user")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

    @POST("/ville/byGouv")
    Call<List<Ville>> getAllVille(@Body HashMap<String, String> map);
}
