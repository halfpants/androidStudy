package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by powflash on 2018. 2. 12..
 */

public class EarthQuake {

    private double qMagnitude;
    private String qLocation;
    private long qDate;
    private String qLink;


    public EarthQuake(double magnitude, String location, long date, String link) {
        qMagnitude = magnitude;
        qLocation = location;
        qDate = date;
        qLink = link;
    }

    public double getMagnitude() {
        return qMagnitude;
    }

    public String getLocation() {
        return qLocation;
    }

    public long getDate() {
        return qDate;
    }

    public String getLink() { return qLink; }
}
