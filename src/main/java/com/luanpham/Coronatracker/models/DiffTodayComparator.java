package com.luanpham.Coronatracker.models;

import java.util.Comparator;

public class DiffTodayComparator implements Comparator<LocationStats> {
    @Override
    public int compare(LocationStats o1, LocationStats o2) {
        return o2.getDiffFromYesterday() - o1.getDiffFromYesterday();
    }
}
