package com.mariem.gojaw.DATA;

import com.mariem.gojaw.models.Event;
import com.mariem.gojaw.models.Gouvernorat;
import com.mariem.gojaw.models.User;
import com.mariem.gojaw.models.Destination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RetrofitInterface {
    @POST("/user/signin" )
    Call<User> executeLogin(@Body HashMap<String,String> map);
    @POST("/user/register-user")
    Call<Void> executeSignup(@Body HashMap<String,String> map);

    @POST("/user/getProfil")
    Call <User> getUserProfil(@Body HashMap<String,String> map);



    @GET("/gouv/all")
    Call<List<Gouvernorat>> getAllGouv();

    @GET("/event/allPublic")
    Call<List<Event>> getAllEvents();

    @POST("/destination/byGouv")
    Call<List<Destination>> getDestinationsByGOUv(@Body HashMap<String, String> map);

    @POST("/event/create")
    Call<Void> createEvent(@Body HashMap<String,Event> map);

    @POST("/event/byGouv")
    Call<List<Event>> getEventsByGOUv(@Body HashMap<String, String> map);

    @POST("/event/byID")
    Call <Event> getEventsByID(@Body HashMap<String, String> map);

    @POST("/event/myEvents")
    Call<List<Event>> myEvents(@Body HashMap<String, String> map);

    @POST("/event/participe")
    Call<Void> particpateToEvent(@Body HashMap<String, String> map);

    @POST("/event/desabonne")
    Call<Void> desabonneEvent(@Body HashMap<String, String> map);

    @POST("/event/participatedEvents")
    Call<List<Event>> getParticipatedEvents(@Body HashMap<String, String> map);

    @POST("/event/privateEvents")
    Call<List<Event>> getPrivateEvents(@Body HashMap<String, String> map);

    @POST("/event/delete")
    Call <Void> deleteEvent(@Body HashMap<String, String> map);

    @POST("/event/Update")
    Call <Void> UpdateEvent(@Body HashMap<String, Event> map);
}
