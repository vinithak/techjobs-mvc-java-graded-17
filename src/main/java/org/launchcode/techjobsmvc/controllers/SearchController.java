package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {

    @GetMapping(value = "")
    public String search(Model model) {
       // model.addAttribute("columns", columnChoices);
        model.addAttribute("searchOption","all");
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        List<Job> jobs = new ArrayList<>();
        if(searchTerm.equalsIgnoreCase("all") || searchTerm.isEmpty()){
            jobs = JobData.findAll();
        }
        else{
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
       // model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs",jobs);
        model.addAttribute("searchOption",searchType);
        return "search";
    }
}

