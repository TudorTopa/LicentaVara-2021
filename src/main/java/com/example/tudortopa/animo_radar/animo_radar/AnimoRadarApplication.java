package com.example.tudortopa.animo_radar.animo_radar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnimoRadarApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimoRadarApplication.class, args);
    }

}
