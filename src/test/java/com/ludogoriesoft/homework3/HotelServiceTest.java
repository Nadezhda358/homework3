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

import java.util.ArrayList;
import java.util.List;
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
    @Test
    public void testUpdateHotelWithValidId() {
        int id = 1;
        Hotel originalHotel = new Hotel(1,"test hotel", "test location", 2, 3, true, false);
        Hotel updatedHotel = new Hotel(1, "updated hotel", "updated location", 3, 4, false, true);
        HotelDTO hotelDTO = new HotelDTO(1, "updated hotel", "updated location", 3, 4, false, true);
        Optional<Hotel> foundHotel = Optional.of(originalHotel);
        when(hotelRepository.findById(id)).thenReturn(foundHotel);
        when(hotelRepository.save(originalHotel)).thenReturn(originalHotel);
        when(modelMapper.map(updatedHotel, HotelDTO.class)).thenReturn(hotelDTO);

        HotelDTO result = hotelService.updateHotel(id, updatedHotel);

        verify(hotelRepository, times(1)).findById(id);
        verify(hotelRepository, times(1)).save(originalHotel);
        verify(modelMapper, times(1)).map(updatedHotel, HotelDTO.class);
        assertEquals(hotelDTO, result);
        assertEquals(updatedHotel.getName(), originalHotel.getName());
        assertEquals(updatedHotel.getLocation(), originalHotel.getLocation());
        assertEquals(updatedHotel.getRoomsCount(), originalHotel.getRoomsCount());
        assertEquals(updatedHotel.getStarsCount(), originalHotel.getStarsCount());
        assertEquals(updatedHotel.isHasSpa(), originalHotel.isHasSpa());
        assertEquals(updatedHotel.isHasPool(), originalHotel.isHasPool());
    }
    @Test
    public void testUpdateHotelWithInvalidId() {
        int id = 2;
        Hotel updatedHotel = new Hotel(1, "updated hotel", "updated location", 3, 4, false, true);
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        HotelDTO result = hotelService.updateHotel(id, updatedHotel);

        verify(hotelRepository, times(1)).findById(id);
        verify(hotelRepository, never()).save(any());
        verify(modelMapper, never()).map(any(), any());
        assertEquals(new HotelDTO(), result);
    }
    @Test
    public void testGetAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel(1, "test hotel 1", "test location 1", 2, 3, true, false));
        hotels.add(new Hotel(2, "test hotel 2", "test location 2", 3, 4, false, true));
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        hotelDTOs.add(new HotelDTO(1, "test hotel 1", "test location 1", 2, 3, true, false));
        hotelDTOs.add(new HotelDTO(2, "test hotel 2", "test location 2", 3, 4, false, true));
        when(hotelRepository.findAll()).thenReturn(hotels);
        when(modelMapper.map(hotels.get(0), HotelDTO.class)).thenReturn(hotelDTOs.get(0));
        when(modelMapper.map(hotels.get(1), HotelDTO.class)).thenReturn(hotelDTOs.get(1));

        List<HotelDTO> result = hotelService.getAllHotels();

        verify(hotelRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(hotels.get(0), HotelDTO.class);
        verify(modelMapper, times(1)).map(hotels.get(1), HotelDTO.class);
        assertEquals(hotelDTOs, result);
    }
    @Test
    public void testGetAllHotelsWithNoHotels() {
        List<Hotel> hotels = new ArrayList<>();
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<HotelDTO> result = hotelService.getAllHotels();

        verify(hotelRepository, times(1)).findAll();
        assertEquals(hotelDTOs, result);
    }
}
