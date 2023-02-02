package com.example.demo.model.dto;

import com.example.demo.model.enums.Difficulties;
import com.example.demo.model.enums.Genre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RoomDTORequest {

    Genre genre;
    Difficulties difficulties;
    String builtDate;
    String name;

}
