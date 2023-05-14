package com.ludogoriesoft.homework3.controllers;

import com.ludogoriesoft.homework3.DTO.HotelDTO;
import com.ludogoriesoft.homework3.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@AllArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable("id") int id) {
        HotelDTO hotelDTO = hotelService.getHotelById(id);
        if (hotelDTO.getId() != 0) {
            return ResponseEntity.ok(hotelDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
