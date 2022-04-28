package com.example.music;

public class MusicItemNotFoundException extends RuntimeException {

    MusicItemNotFoundException(Long id) {
        super("Could not find music item " + id);
    }
}
