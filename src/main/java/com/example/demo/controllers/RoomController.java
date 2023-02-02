package com.example.demo.controllers;

import com.example.demo.model.dto.RoomDTORequest;
import com.example.demo.model.dto.RoomDTOResponse;
import com.example.demo.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Tag(name = "Комнаты")

public class RoomController {


    private final RoomService roomService;

    @PostMapping
    @Operation(summary = "Создание комнаты")
    public ResponseEntity<RoomDTORequest> createRoom(@RequestBody RoomDTORequest roomDTORequest) {
        return ResponseEntity.ok(roomService.create(roomDTORequest));
    }

    @PutMapping
    public ResponseEntity<RoomDTORequest> updateRoom(@RequestBody RoomDTORequest roomDTORequest) {
        return ResponseEntity.ok(roomService.update(roomDTORequest));
    }


    @GetMapping
    public ResponseEntity<RoomDTORequest> getRoom(@RequestParam String name) {
        return ResponseEntity.ok(roomService.get(name));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRoom(@RequestParam String name) {

        roomService.delete(name);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/roomToLandlord")
    public ResponseEntity<RoomDTOResponse> addToParty(@RequestParam String titleNumber, @RequestParam String status) {
        return ResponseEntity.ok(roomService.addToParty(titleNumber, status));
    }


    @GetMapping("/all")
    public ModelMap getAllRooms(@RequestParam(required = false, defaultValue = "1") Integer page,
                                 @RequestParam(required = false, defaultValue = "10") Integer perPage,
                                 @RequestParam(required = false, defaultValue = "name") String sort,
                                 @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order) {
        return roomService.getAllRooms(page, perPage, sort, order);
    }
}
