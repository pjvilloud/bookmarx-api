package me.bookmarx.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String name;

    private String icon;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Bookmark(){

    }

    public Bookmark(String url, String name, String icon, String description) {
        this.url = url;
        this.name = name;
        this.icon = icon;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(id, bookmark.id) &&
                Objects.equals(url, bookmark.url) &&
                Objects.equals(name, bookmark.name) &&
                Objects.equals(icon, bookmark.icon) &&
                Objects.equals(description, bookmark.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, name, icon, description);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
