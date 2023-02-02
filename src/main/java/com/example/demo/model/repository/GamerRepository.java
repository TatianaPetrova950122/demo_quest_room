package com.example.demo.model.repository;

import com.example.demo.model.entity.Gamer;
import com.example.demo.model.enums.GamerStatus;
import com.example.demo.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Long>{

    Optional<Gamer> findByEmail(String email);

    List<Gamer> findAllByStatus(GamerStatus status);

    List<Gamer> findAllByGender(Gender gender);

}
