package com.mariem.gojaw.models;

import java.util.Comparator;

public class Destination {
    private String id;
    private String libelle;
    private String image;
    private String categorie;
    private String distance;

    String latitude;
    String longitude;


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    @Override
    public String toString() {
        return "Destination{" +
                "id='" + id + '\'' +
                ", libelle='" + libelle + '\'' +
                ", image='" + image + '\'' +
                ", categorie='" + categorie + '\'' +
                ", distance='" + distance + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }

   public static final Comparator<Destination> sortByDes=new Comparator<Destination>() {
       @Override
       public int compare(Destination o1, Destination o2) {
           return o1.distance.compareTo(o2.distance);

       }
   };
}
