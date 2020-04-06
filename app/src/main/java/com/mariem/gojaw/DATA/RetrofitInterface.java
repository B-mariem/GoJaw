package com.mariem.gojaw.DATA;

import com.mariem.gojaw.models.Gouvernorat;
import com.mariem.gojaw.models.User;
import com.mariem.gojaw.models.Destination;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/signin" )
    Call<User> executeLogin(@Body HashMap<String,String> map);
    @POST("/user/register-user")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

//    @POST("/ville/byGouv")
//    Call<List<Ville>> getAllVille(@Body HashMap<String, String> map);

    @GET("/gouv/all")
    Call<List<Gouvernorat>> getAllGouv();


    @POST("/destination/byGouv")
    Call<List<Destination>> getByGOUv(@Body HashMap<String, String> map);

    @POST("/event/create")
    Call<Void> createEvent(@Body HashMap<String, String> map);

}
