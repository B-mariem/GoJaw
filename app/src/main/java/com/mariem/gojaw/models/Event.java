package com.mariem.gojaw.models;

import java.util.ArrayList;

public class Event {
    private String id_user;
    private String titre;
    private String date;
    private ArrayList<Destination> destinations;
    private String type;

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

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Destination> destinations) {
        this.destinations = destinations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id_user='" + id_user + '\'' +
                ", titre='" + titre + '\'' +
                ", date='" + date + '\'' +
                ", destinations=" + destinations +
                ", type='" + type + '\'' +
                '}';
    }
}
