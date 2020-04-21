package com.luanpham.Coronatracker.models;

import java.util.Comparator;

public class CountryComparator implements Comparator<LocationStats> {
    @Override
    public int compare(LocationStats o1, LocationStats o2) {
        return o1.getCountry().compareTo(o2.getCountry());
    }
}
