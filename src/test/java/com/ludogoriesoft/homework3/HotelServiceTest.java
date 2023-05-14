package com.ludogoriesoft.homework3;

import com.ludogoriesoft.homework3.DTO.HotelDTO;
import com.ludogoriesoft.homework3.entities.Hotel;
import com.ludogoriesoft.homework3.repositories.HotelRepository;
import com.ludogoriesoft.homework3.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HotelServiceTest {
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    private HotelService hotelService;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testDeleteExistingHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setLocation("Test Location");
        hotel.setRoomsCount(100);
        hotel.setStarsCount(4);
        hotel.setHasSpa(true);
        hotel.setHasPool(false);
        hotelRepository.save(hotel);
        hotelService.deleteHotelById(hotel.getId());
        assertFalse(hotelRepository.findById(hotel.getId()).isPresent());
    }
    @Test
    public void testCreateHotelWithNull() {
        Hotel hotel = null;
        HotelDTO hotelDTO = null;
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(modelMapper.map(hotel, HotelDTO.class)).thenReturn(hotelDTO);

        HotelDTO result = hotelService.createHotel(hotel);

        assertNull(result);
    }

    @Test
    public void testCreateHotel() {
        Hotel hotel = new Hotel();
        HotelDTO hotelDTO = new HotelDTO();
        when(hotelRepository.save(hotel)).thenReturn(hotel);
        when(modelMapper.map(hotel, HotelDTO.class)).thenReturn(hotelDTO);

        HotelDTO result = hotelService.createHotel(hotel);

        verify(hotelRepository, times(1)).save(hotel);
        verify(modelMapper, times(1)).map(hotel, HotelDTO.class);
        assertEquals(hotelDTO, result);
    }

    @Test
    public void testGetHotelByIdWithValidId() {
        int id = 1;
        Hotel hotel = new Hotel(1, "test hotel", "test location", 200, 3, true, true);
        HotelDTO hotelDTO = new HotelDTO(1, "test hotel", "test location", 200, 3, true, true);
        when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));
        when(modelMapper.map(hotel, HotelDTO.class)).thenReturn(hotelDTO);

        HotelDTO result = hotelService.getHotelById(id);

        verify(hotelRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(hotel, HotelDTO.class);
        assertEquals(hotelDTO, result);
    }
    @Test
    public void testGetHotelByIdWithInvalidId() {
        int id = 2;
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        HotelDTO result = hotelService.getHotelById(id);

        verify(hotelRepository, times(1)).findById(id);
        assertEquals(new HotelDTO(), result);
    }
}
