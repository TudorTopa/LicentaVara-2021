package com.example.tudortopa.animo_radar.animo_radar;

import com.example.tudortopa.animo_radar.animo_radar.model.General.ERole;
import com.example.tudortopa.animo_radar.animo_radar.model.General.Role;
import com.example.tudortopa.animo_radar.animo_radar.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
public class AnimoRadarApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimoRadarApplication.class, args);
    }
    @Bean
    public CommandLineRunner checkRoles(RoleRepository RoleRepository) {
        return args -> {
            List<Role> roles = RoleRepository.findAll();
            if (roles.isEmpty()) {
                Stream.of(new Role(ERole.ROLE_USER),
                        new Role(ERole.ROLE_ADMIN))
                        .forEach(RoleRepository::save);
            }
        };
    }

}
