package com.example.demo.controllers;

import com.example.demo.model.dto.PartyDTO;
import com.example.demo.service.PartyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parties")
@RequiredArgsConstructor
@Tag(name = "Группа")
public class PartyController {

    private final PartyService partyService;

    @PutMapping
    public ResponseEntity<PartyDTO> openParty(@RequestBody PartyDTO partyDTO) {
        return ResponseEntity.ok(partyService.open(partyDTO));
    }

    @GetMapping
    public ResponseEntity<PartyDTO> getParty(@RequestParam String titleNumber) {
        return ResponseEntity.ok(partyService.get(titleNumber));
    }

    @PostMapping("/partyForEverybody")
    public ResponseEntity<PartyDTO> setGamer(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(gamerService.set(name, email));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> closeParty(@RequestParam String titleNumber) {

        partyService.close(titleNumber);
        return ResponseEntity.ok().build();
    }

}
