package me.bookmarx.api.controller;

import me.bookmarx.api.model.Dashboard;
import me.bookmarx.api.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @GetMapping("/{id}")
    public Dashboard getDashboardByName(@PathVariable Long id, @RequestParam Boolean withCategories){
        Optional<Dashboard> dashboard;
        if(withCategories) {
            dashboard = dashboardRepository.findByIdWithCategories(id);
        } else {
            dashboard = dashboardRepository.findById(id);
        }
        if(dashboard.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver le dashboard d'identifiant " + id);
        }
        return dashboard.get();
    }

    @GetMapping("")
    public List<Dashboard> getAllDashboards(){
        return dashboardRepository.findAll();
    }

    @GetMapping(value = "", params = "name")
    public Set<Dashboard> getAllDashboardsWithNameLike(@RequestParam String name){
        return dashboardRepository.findByNameContaining(name);
    }

    @PostMapping("")
    public Dashboard createDashboard(@RequestBody Dashboard dashboard){
        //To be safe, we clear any categories that might have been sent along
        dashboard.setCategories(new HashSet<>());
        return dashboardRepository.save(dashboard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDashboard(@PathVariable Long id){
        dashboardRepository.deleteById(id);
    }

    @PatchMapping("/{dashboardId}")
    public Dashboard editDashboard(@PathVariable Long dashboardId, @RequestBody Dashboard dashboard){
        if(!dashboardId.equals(dashboard.getId())){
            throw new IllegalArgumentException("L'identifiant du dashboard ne correspond pas à celui de l'URL !");
        }
        return dashboardRepository.save(dashboard);
    }
}
