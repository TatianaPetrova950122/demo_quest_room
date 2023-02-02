package com.example.demo.service.impl;

import com.example.demo.exeptions.CustomException;
import com.example.demo.model.dto.GamerDTO;
import com.example.demo.model.entity.Gamer;
import com.example.demo.model.enums.GamerStatus;
import com.example.demo.model.repository.GamerRepository;
import com.example.demo.service.GamerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class GamerServiceImpl implements GamerService {

    private final GamerRepository gamerRepository;
    private final ObjectMapper mapper;


    @Override
    public GamerDTO createGamer(GamerDTO gamerDTO) {
        gamerRepository.findByEmail(gamerDTO.getEmail()).ifPresent(
                h -> {
                    throw new CustomException("Игрок с таким email уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        Gamer party = mapper.convertValue(gamerDTO, Gamer.class);
        Gamer save = gamerRepository.save(party);

        return mapper.convertValue(save, GamerDTO.class);
    }

    @Override
    public GamerDTO update(GamerDTO gamerDTO) {
      return null;
    }

    @Override
    public GamerDTO get(String email) {
        return mapper.convertValue(getGamer(email), GamerDTO.class);
    }

    @Override
    public void delete(String email) {
        Gamer gamer = getGamer(email);
        gamer.setStatus(GamerStatus.DELETED);
        gamer.setUpdatedAt(LocalDateTime.now());
        gamerRepository.save(gamer);
    }

    @Override
    public Gamer getGamer(String email) {
        return gamerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("Игрок с таким email не найден", HttpStatus.NOT_FOUND));
    }
}
