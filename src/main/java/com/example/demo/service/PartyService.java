package com.example.demo.service;

import com.example.demo.model.dto.PartyDTO;
import com.example.demo.model.entity.Party;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;

public interface PartyService {

    PartyDTO create(PartyDTO partyDTO);

    PartyDTO open(PartyDTO partyDTO);

    PartyDTO get(String titleNumber);

    PartyDTO set(String titleNumber);

    void close(String titleNumber);

    Party getParty(String titleNumber);

    Party setParty(String titleNumber);

    PartyDTO addToGamer(String name, String email);

    ModelMap getAllParties(Integer page, Integer perPage, String sort, Sort.Direction order);
}
