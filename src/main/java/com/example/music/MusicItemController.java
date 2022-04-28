package com.example.music;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MusicItemController {
    private final MusicItemRepository repository;

    MusicItemController(MusicItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value="/items")
    List<MusicItem> allMusicItems() {
        return repository.findAll();
    }

    @GetMapping("/items/{id}")
    MusicItem getMusicItem(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MusicItemNotFoundException(id));
    }

    @GetMapping("/itemsrp")
    MusicItem getMusicItemrp(@RequestParam(value = "id",
            defaultValue = "1", required = false) Long id){
        return repository.findById(id)
                .orElseThrow(() -> new MusicItemNotFoundException(id));
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    MusicItem newItem(@RequestBody MusicItem item) {
        return repository.save(item);
    }

    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    MusicItem updateMusicItem(@RequestBody MusicItem newItem, @PathVariable Long id) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setArtist_group(newItem.getArtist_group());
                    item.setGenre(newItem.getGenre());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });
    }

    @DeleteMapping("/items/{id}")
    void deleteMusicItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
