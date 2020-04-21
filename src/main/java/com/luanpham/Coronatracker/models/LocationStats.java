package com.luanpham.Coronatracker.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LocationStats {
    private String state;
    private String country;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    private int latestTotalCases;
    private int diffFromYesterday;

    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCase=" + latestTotalCases +
                '}';
    }

    public int getDiffFromYesterday() {
        return diffFromYesterday;
    }

    public void setDiffFromYesterday(int diffFromYesterday) {
        this.diffFromYesterday = diffFromYesterday;
    }

    public LocationStats(String country, String state, int latestTotalCases, int diffFromYesterday) {
        this.country = country;
        this.state = state;
        this.latestTotalCases = latestTotalCases;
        this.diffFromYesterday = diffFromYesterday;
    }

    public LocationStats() {
    }

    public LocationStats add(LocationStats b) {
        return new LocationStats(this.getCountry(), this.getState(),
                this.getLatestTotalCases() + b.getLatestTotalCases(),
                this.getDiffFromYesterday() + b.getDiffFromYesterday());
    }

//    @Override
//    public int compareTo(LocationStats o) {
//        return o.getDiffFromYesterday() - this.getDiffFromYesterday();
//    }
}

