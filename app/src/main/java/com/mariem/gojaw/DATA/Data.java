package com.mariem.gojaw.DATA;

import com.mariem.gojaw.models.Destination;
import com.mariem.gojaw.models.params;

import java.util.ArrayList;

public class Data {
    private static final Data ourInstance = new Data();
    private static ArrayList<Destination> mDataSelected = new ArrayList<>();
    private static ArrayList<Destination> mData = new ArrayList<>();
    private static  ArrayList<params> mDataparams=new ArrayList<>();

    public static ArrayList<params> getmDataparams() {
        return mDataparams;
    }

    public static void setmDataparams(ArrayList<params> mDataparams) {
        Data.mDataparams = mDataparams;
    }

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
