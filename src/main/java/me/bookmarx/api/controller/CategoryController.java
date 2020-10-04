package me.bookmarx.api.controller;

import me.bookmarx.api.model.Category;
import me.bookmarx.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new EntityNotFoundException("Impossible de trouver la catégorie " + id);
        }
        return category.get();
    }

    @PostMapping("")
    public Category createCategory(@RequestBody Category category){
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

    @PatchMapping("/{categoryId}")
    public Category editCategory(@PathVariable Long categoryId, @RequestBody Category category){
        if(!categoryId.equals(category.getId())){
            throw new IllegalArgumentException("L'identifiant de la catégorie ne correspond pas à celui de l'URL !");
        }
        return categoryRepository.save(category);
    }
}
