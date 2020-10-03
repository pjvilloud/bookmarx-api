package me.bookmarx.api.controller;

import me.bookmarx.api.model.Dashboard;
import me.bookmarx.api.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/{id}")
    public Dashboard getDashboardByName(@PathVariable Long id){
        Optional<Dashboard> dashboard = dashboardRepository.findById(id);
        if(dashboard.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver le dashboard d'identifiant " + id);
        }
        return dashboard.get();
    }

    @GetMapping("")
    public List<Dashboard> getAllDashboards(){
        return dashboardRepository.findAll();
    }

    @PostMapping("")
    public Dashboard createDashboard(@RequestBody Dashboard dashboard){
        //To be safe, we clear any bookmarks tht might have been sent along
        dashboard.setCategories(new ArrayList<>());
        return dashboardRepository.save(dashboard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDashboard(@PathVariable Long id){
        dashboardRepository.deleteById(id);
    }
}
