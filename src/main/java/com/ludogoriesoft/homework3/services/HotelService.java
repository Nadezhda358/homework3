package com.ludogoriesoft.homework3.services;

import com.ludogoriesoft.homework3.DTO.HotelDTO;
import com.ludogoriesoft.homework3.entities.Hotel;
import com.ludogoriesoft.homework3.repositories.HotelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels
                .stream()
                .map(this::hotelToHotelDTO)
                .collect(Collectors.toList());
    }
    public HotelDTO createHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotelToHotelDTO(hotel);
    }
    public void deleteHotelById(int id) {
        hotelRepository.deleteById(id);
    }
    public HotelDTO getHotelById(int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotelToHotelDTO(hotel.get());
    }

    private HotelDTO hotelToHotelDTO(Hotel hotel){
        return modelMapper.map(hotel, HotelDTO.class);
    }

}
