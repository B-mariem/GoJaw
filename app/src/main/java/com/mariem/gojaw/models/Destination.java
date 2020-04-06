package com.mariem.gojaw.models;

public class Destination{
    private String id;
    private String libelle;
    private Position position;
    private String image;
    private String categorie;
    private String distance;
    private int heure_arrive_des;
    private int minute_arrive_des;

    public int getHeure_arrive_des() {
        return heure_arrive_des;
    }

    public void setHeure_arrive_des(int heure_arrive_des) {
        this.heure_arrive_des = heure_arrive_des;
    }

    public int getMinute_arrive_des() {
        return minute_arrive_des;
    }

    public void setMinute_arrive_des(int minute_arrive_des) {
        this.minute_arrive_des = minute_arrive_des;
    }

    private Boolean isSelected;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }



    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id='" + id + '\'' +
                ", libelle='" + libelle + '\'' +
                ", distance='" + distance + '\'' +
                ", temps_arrive=" + heure_arrive_des +":"+minute_arrive_des+
                ", isSelected=" + isSelected +
                '}';
    }
}
