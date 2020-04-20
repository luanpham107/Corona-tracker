package com.luanpham.Coronatracker.models;

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
}
