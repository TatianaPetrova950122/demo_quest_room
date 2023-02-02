package com.example.demo.model.entity;


import com.example.demo.model.enums.PartyStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "parties")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title_number")
    String titleNumber;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    List<Gamer> gamers;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    PartyStatus status = PartyStatus.OPEN;
}
