package com.example.android.quakereport;

public class QuakeData {
    private double mag;
    private String city;
    private long date;

    public QuakeData(double mag, String city, long date) {
        this.mag = mag;
        this.city = city;
        this.date = date;
    }

    public double getMag() {
        return mag;
    }

    public void setMag(float mag) {
        this.mag = mag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
