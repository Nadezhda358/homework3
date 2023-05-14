package com.ludogoriesoft.homework3.controllers;

import com.ludogoriesoft.homework3.DTO.HotelDTO;
import com.ludogoriesoft.homework3.entities.Hotel;
import com.ludogoriesoft.homework3.services.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody Hotel hotel,UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/api/v1/hotels/{id}").buildAndExpand(hotelService.createHotel(hotel).getId()).toUri();
        return ResponseEntity.
                created(location).
                build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        HotelDTO hotelDTO = hotelService.updateHotel(id, hotel);
        if (hotelDTO.getId() != 0) {
            return ResponseEntity.ok(hotelDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HotelDTO> deleteHotelById(@PathVariable("id") int id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.noContent().build();
    }
}
