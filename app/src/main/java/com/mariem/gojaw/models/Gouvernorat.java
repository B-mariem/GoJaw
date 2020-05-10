package com.mariem.gojaw.models;

import java.util.Comparator;

public class Gouvernorat {
    private String id ;
    private String gouv;
    private String image;

    @Override
    public String toString() {
        return "Gouvernorat{" +
                "id='" + id + '\'' +
                ", gouv='" + gouv + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGouv() {
        return gouv;
    }

    public void setGouv(String gouv) {
        this.gouv = gouv;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static final Comparator<Gouvernorat> sortByGouv =new Comparator<Gouvernorat>() {
        @Override
        public int compare(Gouvernorat o1, Gouvernorat o2) {
            return o1.gouv.compareTo(o2.gouv);
        }
    };
}
