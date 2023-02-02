package com.example.demo.model.dto;

import com.example.demo.model.enums.PartyStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartyDTO {

    GamerDTO gamerDTO;
    int numOfPlayers;

}
