package com.luanpham.Coronatracker.controllers;

import com.luanpham.Coronatracker.models.LocationStats;
import com.luanpham.Coronatracker.services.CoronaTrackerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaTrackerDataService coronaTrackerDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats =  coronaTrackerDataService.getAllStats();
        int totalCaseReported =  allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totelNewCases =  allStats.stream().mapToInt(stat -> stat.getDiffFromYesterday()).sum();
        model.addAttribute("totalCaseReported", totalCaseReported);
        model.addAttribute("totalNewCaseReported", totelNewCases);
        model.addAttribute("locationStats", allStats);
        return "home";
    }
}
