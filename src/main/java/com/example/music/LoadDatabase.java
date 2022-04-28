package com.example.music;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MusicItemRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new MusicItem("Imagine", "John Lennon", "Soft Rock")));
            log.info("Preloading " + repository.save(new MusicItem("Ghost Town", "The Specials", "Ska")));
            log.info("Preloading " + repository.save(new MusicItem("That's Entertainment", "The Jam", "Indie")));
        };
    }
}
