package com.example.demo.service.impl;

import com.example.demo.model.dto.PartyDTO;
import com.example.demo.model.entity.Party;
import com.example.demo.model.enums.PartyStatus;
import com.example.demo.model.repository.PartyRepository;
import com.example.demo.service.PartyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;
    private final ObjectMapper mapper;

    @Override
    public PartyDTO create(PartyDTO partyDTO) {
//        partyRepository.findByName(partyDTO.getTitleNumber()).ifPresent(
//                h -> {
//                    throw new CustomException("Группа с таким названием уже существует", HttpStatus.BAD_REQUEST);
//                }
//        );
//
//        Party room = mapper.convertValue(partyDTO, Party.class);
//        Party save = partyRepository.save(room);
//
//        return mapper.convertValue(save, PartyDTO.class);
        return null;
    }

//    @Override
//    public PartyDTO open(PartyDTO partyDTO) {
//        Party party = getParty(partyDTO.getTitleNumber());
//
//        party.setPartyStatus(party.getPartyStatus() == null ? )
//    }

    @Override
    public PartyDTO get(String titleNumber) {
        return mapper.convertValue(getParty(titleNumber), PartyDTO.class);
    }

    @Override
    public void close(String titleNumber) {
        Party party = getParty(titleNumber);
        party.setStatus(PartyStatus.CLOSE);
        party.setUpdatedAt(LocalDateTime.now());
        partyRepository.save(party);
    }

    @Override
    public Party getParty(String titleNumber) {
        return null;
    }

    @Override
    public Party setParty(String titleNumber) {
        return null;
    }

    @Override
    public PartyDTO addToGamer(String name, String email) {
        return null;
    }

    @Override
    public ModelMap getAllParties(Integer page, Integer perPage, String sort, Sort.Direction order) {
        return null;
    }
}
