package me.bookmarx.api.repository;

import me.bookmarx.api.model.Dashboard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    public Set<Dashboard> findByNameContaining(String name);

    //Why it fetches the categories' bookmarks ?
    @EntityGraph(attributePaths = {"categories"})
    @Query("from Dashboard where id = ?1")
    public Optional<Dashboard> findByIdWithCategories(Long id);
}
