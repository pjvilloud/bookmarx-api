package me.bookmarx.api.controller;

import me.bookmarx.api.model.Bookmark;
import me.bookmarx.api.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @PostMapping("")
    public Bookmark createBookmark(@RequestBody Bookmark bookmark){
        return bookmarkRepository.save(bookmark);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookmark(@PathVariable Long id){
        bookmarkRepository.deleteById(id);
    }

    @PatchMapping("/{bookmarkId}")
    public Bookmark editBookmark(@PathVariable Long bookmarkId, @RequestBody Bookmark bookmark){
        if(!bookmarkId.equals(bookmark.getId())){
            throw new IllegalArgumentException("L'identifiant du favori ne correspond pas à celui de l'URL !");
        }
        return bookmarkRepository.save(bookmark);
    }
}
