package me.bookmarx.api.controller;

import me.bookmarx.api.model.Category;
import me.bookmarx.api.model.Dashboard;
import me.bookmarx.api.repository.CategoryRepository;
import me.bookmarx.api.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dashboards/{dashboardId}/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    @PostMapping("")
    public Category createCategory(@PathVariable Long dashboardId, @RequestBody Category category){
        Optional<Dashboard> dashboard = dashboardRepository.findById(dashboardId);
        if(dashboard.isEmpty()){
            throw new IllegalArgumentException("Le dashboard a qui appartient cette catégorie doit être précisé !");
        } else {
            category.setDashboard(dashboard.get());
        }
        if(categoryRepository.existsByName(category.getName())){
            throw new DuplicateKeyException("La catégorie '" + category.getName() + "' existe déjà dans ce dashboard !");
        }
        //To be safe, we clear any bookmarks that might have been sent along
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
    }
}
