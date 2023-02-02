package com.example.demo.service.impl;

import com.example.demo.exeptions.CustomException;
import com.example.demo.model.dto.GamerDTO;
import com.example.demo.model.dto.PartyDTO;
import com.example.demo.model.dto.RoomDTORequest;
import com.example.demo.model.entity.Gamer;
import com.example.demo.model.entity.Party;
import com.example.demo.model.entity.Room;
import com.example.demo.model.enums.PartyStatus;
import com.example.demo.model.repository.PartyRepository;
import com.example.demo.service.PartyService;
import com.example.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PartyDTO open(PartyDTO partyDTO) {
        Party party = getParty(partyDTO.getTitleNumber());

        party.setPartyStatus(party.getPartyStatus() == null ?)
    }

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
    public Party getParty(String partyDTO) {
        return partyRepository.findByName(partyDTO)
                .orElseThrow(() -> new CustomException("Группа с таким названием не найдена", HttpStatus.NOT_FOUND));
    }

    @Override
    public Party setParty(String titleNumber) {
        return null;
    }

    @Override
    public PartyDTO addToGamer(String name, String email) {
        Gamer gamer = gamerService.getGamer(email);
        Party party = getParty(name);
        party.setGamer(gamer);
        Party save = partyRepository.save(party);
        PartyDTO response = mapper.convertValue(save, PartyDTO.class);
        response.setGamerDTO(mapper.convertValue(gamer, GamerDTO.class));
        return response;
    }

    @Override
    public ModelMap getAllParties(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Party> pageResult = partyRepository.findAll(pageRequest);

        List<PartyDTO> content = pageResult.getContent().stream()
                .map(h -> mapper.convertValue(h, PartyDTO.class))
                .collect(Collectors.toList());

        PagedListHolder<PartyDTO> result = new PagedListHolder<>(content);

        result.setPage(page);
        result.setPageSize(perPage);

        ModelMap map = new ModelMap();
        map.addAttribute("content", result.getPageList());
        map.addAttribute("pageNumber", page);
        map.addAttribute("pageSize", result.getPageSize());
        map.addAttribute("totalPages", result.getPageCount());

        return map;
    }

    public Long multiply(int x, int y) {

        return (long) x * y;
    }
}
