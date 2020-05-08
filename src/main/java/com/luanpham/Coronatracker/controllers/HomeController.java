package com.luanpham.Coronatracker.controllers;

import com.luanpham.Coronatracker.models.CountryComparator;
import com.luanpham.Coronatracker.models.DiffTodayComparator;
import com.luanpham.Coronatracker.models.LocationStats;
import com.luanpham.Coronatracker.services.CoronaTrackerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    CoronaTrackerDataService coronaTrackerDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaTrackerDataService.getAllStats();
        LocalDateTime TimeGotTheData = coronaTrackerDataService.getDateGotTheData();

        Collections.sort(allStats, new DiffTodayComparator());
        // Get from top of the list
        List<LocationStats> top10CasesStats = allStats.subList(0, 10);
        // Get from down of the list
        List<LocationStats> top10RecoverStats = allStats.subList(allStats.size() - 10, allStats.size());
        // Collections.reverse(top10RecoverStats);
        int totalCaseReported =  allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases =  allStats.stream().mapToInt(stat -> stat.getDiffFromYesterday()).sum();
        model.addAttribute("totalCaseReported", totalCaseReported);
        model.addAttribute("totalNewCaseReported", totalNewCases);
        model.addAttribute("locationStats", allStats);
        model.addAttribute("top10CasesStats", top10CasesStats);
        model.addAttribute("top10RecoverStats", top10RecoverStats);
        model.addAttribute("TimeGotTheData", TimeGotTheData);
        return "home";
    }
}

