package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.General.ERole;
import com.example.tudortopa.animo_radar.animo_radar.model.General.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    Role findByName(ERole eRole);
}
