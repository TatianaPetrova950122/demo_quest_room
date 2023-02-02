package com.example.demo.model.entity;

import com.example.demo.model.enums.Difficulties;
import com.example.demo.model.enums.Genre;
import com.example.demo.model.enums.RoomStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rooms")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Room {


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Genre genre;
    Difficulties difficulties;

    @Column(name = "built_date")
    String builtDate;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    Party parties;

    @Column(name = "num_of_players")
    int numOfPlayers;

    String name;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    RoomStatus status = RoomStatus.CREATED;
}
