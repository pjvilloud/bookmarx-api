package me.bookmarx.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "dashboard")
    private List<Category> categories = new ArrayList<>();

    public Dashboard() {
    }

    public Dashboard(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
