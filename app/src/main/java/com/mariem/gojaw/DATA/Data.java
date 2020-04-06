package com.mariem.gojaw.DATA;

import com.mariem.gojaw.models.Destination;

import java.util.ArrayList;

public class Data {
    private static final Data ourInstance = new Data();
    private static ArrayList<Destination> mDataSelected = new ArrayList<>();
    private static ArrayList<Destination> mData = new ArrayList<>();



    public static ArrayList<Destination> getmData() {
        return mData;
    }

    public static void setmData(ArrayList<Destination> mData) {
        Data.mData = mData;
    }

    public static ArrayList<Destination> getmDataSelected() {
        return mDataSelected;
    }

    public static void setmDataSelected(ArrayList<Destination> mDataSelected) {
        Data.mDataSelected = mDataSelected;
    }

    public static Data getInstance() {
        return ourInstance;
    }
}
