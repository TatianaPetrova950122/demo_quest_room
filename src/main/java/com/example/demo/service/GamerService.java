package com.example.demo.service;

import com.example.demo.model.dto.GamerDTO;
import com.example.demo.model.entity.Gamer;

public interface GamerService {

    GamerDTO createGamer(GamerDTO gamerDTO);

    GamerDTO update(GamerDTO gamerDTO);

    GamerDTO get(String email);

    void delete(String email);

    Gamer getGamer(String email);
}
