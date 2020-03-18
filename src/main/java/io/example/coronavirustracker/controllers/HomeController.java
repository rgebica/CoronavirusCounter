package io.example.coronavirustracker.controllers;

import io.example.coronavirustracker.CoronaVirusDataService;
import io.example.coronavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream()
                .mapToInt(stat -> stat.getLatestCases())
                .sum();

        int totalNewCases = allStats.stream()
                .mapToInt(stat -> stat.getDiffFromPrevDay())
                .sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
