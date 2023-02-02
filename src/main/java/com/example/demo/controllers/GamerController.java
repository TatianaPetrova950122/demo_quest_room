package com.example.demo.controllers;

import com.example.demo.model.dto.GamerDTO;
import com.example.demo.service.GamerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/gamers")
@RequiredArgsConstructor

public class GamerController {

    private final GamerService gamerService;

    @PostMapping
    public GamerDTO createGamer(@RequestBody GamerDTO gamerDTO) {
        return gamerService.createGamer(gamerDTO);
    }

    @PutMapping
    public ResponseEntity<GamerDTO> updateRoom(@RequestBody GamerDTO userDTO) {
        return ResponseEntity.ok(gamerService.update(userDTO));
    }


    @GetMapping
    public ResponseEntity<GamerDTO> getRoom(@RequestParam String email) {
        return ResponseEntity.ok(gamerService.get(email));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteRoom(@RequestParam String email) {

        gamerService.delete(email);
        return ResponseEntity.ok().build();
    }
}
