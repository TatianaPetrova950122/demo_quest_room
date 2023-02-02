package com.example.demo.service;


import com.example.demo.model.dto.RoomDTORequest;
import com.example.demo.model.dto.RoomDTOResponse;
import org.springframework.data.domain.Sort;
import org.springframework.ui.ModelMap;

public interface RoomService {

    RoomDTORequest create(RoomDTORequest houseDTORequest);

    RoomDTORequest update(RoomDTORequest houseDTORequest);

    RoomDTORequest get(String name);

    void delete(String name);

    RoomDTOResponse addToParty(String titleNumber, String status);

    ModelMap getAllRooms(Integer page, Integer perPage, String sort, Sort.Direction order);

}
