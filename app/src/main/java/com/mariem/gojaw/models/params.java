package com.mariem.gojaw.models;

import java.sql.Time;

public class params {
    private String tempsArrive;
    private String destination;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTempsArrive() {
        return tempsArrive;
    }

    public void setTempsArrive(String tempsArrive) {
        this.tempsArrive = tempsArrive;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "   "+destination+
                " à " + tempsArrive +"h \n";
    }
}
