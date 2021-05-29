package com.example.tudortopa.animo_radar.animo_radar.repository;

import com.example.tudortopa.animo_radar.animo_radar.model.General.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(long id);

    Optional<User> findUserByUserName(String username);

    List<User> findAll();

    boolean existsByUserName(String username);

}
