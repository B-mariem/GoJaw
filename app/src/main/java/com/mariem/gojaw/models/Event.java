package com.mariem.gojaw.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class Event {
    private String _id;
    private String id_user;
    private String titre;
    private String date;
    private String gouv;
    private ArrayList<params> params;
    private String type;
    private String createdBy;
    private ArrayList <String> participants;

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<params> getParams() {
        return params;
    }

    public void setParams(ArrayList<params> params) {
        this.params = params;
    }

    public String getGouv() {
        return gouv;
    }

    public void setGouv(String gouv) {
        this.gouv = gouv;
    }

    @Override
    public String toString() {
        return "        Event : " + titre +"\n"+
                "\n"+
                "date : " + date  +"\n"+
                "\n"+
                "Destinations : \n"+params +"\n";
    }
    public static final Comparator<Event> sortByDate=new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.date.compareTo(o2.date);
        }
    };
}
