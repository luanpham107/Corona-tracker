package com.luanpham.Coronatracker.services;

import com.luanpham.Coronatracker.models.CountryComparator;
import com.luanpham.Coronatracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class CoronaTrackerDataService {
    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationStats> allStats = new ArrayList<LocationStats>();
    public List<LocationStats> getAllStats() {
        return allStats;
    }
    @PostConstruct
    @Scheduled(cron = "10 * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);


        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int latestTotalCases = Integer.parseInt(record.get(record.size() - 1));
            locationStat.setLatestTotalCases(latestTotalCases);
            locationStat.setDiffFromYesterday(latestTotalCases - Integer.parseInt(record.get(record.size() - 2)));
            newStats.add(locationStat);
        }

        Collections.sort(newStats, new CountryComparator());
        for(int i = 0 ; i < newStats.size() - 1; i++)
        {
            LocationStats l1 = newStats.get(i);
            LocationStats l2 = newStats.get(i+1);

            if(l1.getCountry().equals(l2.getCountry())){
                newStats.remove(l1);
                newStats.remove(l2);
                newStats.add(i, l1.add(l2));
                i--; // Check again L2
            }
        }

        this.allStats = newStats;

    }

}
