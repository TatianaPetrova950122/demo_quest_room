package com.example.demo.model.repository;

import com.example.demo.model.entity.Party;
import com.example.demo.model.enums.PartyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByName(String titleNumber);

    List<Party> findAllByStatus(PartyStatus status);

}
