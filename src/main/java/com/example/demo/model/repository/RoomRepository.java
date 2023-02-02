package com.example.demo.model.repository;

import com.example.demo.model.entity.Room;
import com.example.demo.model.enums.Difficulties;
import com.example.demo.model.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    Optional<Room> findByName(String name);

    List<Room> findAllByGenre(Genre genre);

    @Query("select room from Room room where room.genre = :genre")
    List<Room> getRooms(@Param("genre") Genre genre);

    @Query(value = "select * from rooms where rooms.genre = :genre", nativeQuery = true)
    List<Room> getRoomsNative(@Param("genre") Genre genre);

    List<Room> findAllByDifficulties(Difficulties difficulties);

    @Query("select room from Room room where room.difficulties = :difficulties")
    List<Room> getRooms(@Param("difficulties") Difficulties difficulties);

    @Query(value = "select * from rooms where rooms.difficulties = :difficulties", nativeQuery = true)
    List<Room> getRoomsNative(@Param("difficulties") Difficulties difficulties);

}
