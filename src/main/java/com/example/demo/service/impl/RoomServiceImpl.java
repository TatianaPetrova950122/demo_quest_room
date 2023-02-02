package com.example.demo.service.impl;

import com.example.demo.exeptions.CustomException;
import com.example.demo.model.dto.PartyDTO;
import com.example.demo.model.dto.RoomDTORequest;
import com.example.demo.model.dto.RoomDTOResponse;
import com.example.demo.model.entity.Party;
import com.example.demo.model.entity.Room;
import com.example.demo.model.enums.RoomStatus;
import com.example.demo.model.repository.RoomRepository;
import com.example.demo.service.PartyService;
import com.example.demo.service.RoomService;
import com.example.demo.utils.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class RoomServiceImpl implements RoomService {

    private final PartyService partyService;
    private final RoomRepository roomRepository;
    private final ObjectMapper mapper;

    @Override
    public RoomDTORequest create(RoomDTORequest roomDTORequest) {
        roomRepository.findByName(roomDTORequest.getName()).ifPresent(
                h -> {
                    throw new CustomException("Комната с таким названием уже существует", HttpStatus.BAD_REQUEST);
                }
        );

        Room room = mapper.convertValue(roomDTORequest, Room.class);
        roomRepository.save(room);

        return mapper.convertValue(room, RoomDTORequest.class);
    }

    @Override
    public RoomDTORequest update(RoomDTORequest roomDTORequest) {
        Room room = getRoom(roomDTORequest.getName());

        room.setGenre(roomDTORequest.getGenre() == null ? room.getGenre() : roomDTORequest.getGenre());
        room.setDifficulties(roomDTORequest.getDifficulties() == null ? room.getDifficulties() : roomDTORequest.getDifficulties());
        room.setBuiltDate(StringUtils.isBlank(roomDTORequest.getBuiltDate()) ? room.getBuiltDate() : roomDTORequest.getBuiltDate());
        room.setUpdatedAt(LocalDateTime.now());
        room.setStatus(RoomStatus.UPDATED);

        return mapper.convertValue(roomRepository.save(room), RoomDTORequest.class);
    }

    @Override
    public RoomDTORequest get(String name) {
        return mapper.convertValue(getRoom(name), RoomDTORequest.class);
    }

    @Override
    public void delete(String name) {
        Room room = getRoom(name);
        room.setStatus(RoomStatus.DELETED);
        room.setUpdatedAt(LocalDateTime.now());
        roomRepository.save(room);
    }

    @Override
    public RoomDTOResponse addToParty(String titleNumber, String status) {
        Party party = partyService.getParty(status);
        Room room = getRoom(titleNumber);
        room.setParty(party);
        Room save = roomRepository.save(room);
        RoomDTOResponse response = mapper.convertValue(save, RoomDTOResponse.class);
        response.setPartyDTO(mapper.convertValue(party, PartyDTO.class));
        return response;
    }

    private Room getRoom(String roomDTO) {
        return roomRepository.findByName(roomDTO)
                .orElseThrow(() -> new CustomException("Комната с таким названием не найдена", HttpStatus.NOT_FOUND));
    }

    @Override
    public ModelMap getAllRooms(Integer page, Integer perPage, String sort, Sort.Direction order) {
        Pageable pageRequest = PaginationUtil.getPageRequest(page, perPage, sort, order);
        Page<Room> pageResult = roomRepository.findAll(pageRequest);

        List<RoomDTORequest> content = pageResult.getContent().stream()
                .map(h -> mapper.convertValue(h, RoomDTORequest.class))
                .collect(Collectors.toList());

        PagedListHolder<RoomDTORequest> result = new PagedListHolder<>(content);

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

        return (long)x * y;

    }


}

