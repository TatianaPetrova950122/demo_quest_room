package com.example.demo.model.dto;

import com.example.demo.model.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GamerDTO {
    Integer age;
    String firstName;
    String lastName;
    Gender gender;
    String email;
}
