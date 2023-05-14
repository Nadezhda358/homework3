package com.ludogoriesoft.homework3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private int id;
    private String name;
    private String location;
    private int roomsCount;
    private int starsCount;
    private boolean hasSpa;
    private boolean hasPool;
}
