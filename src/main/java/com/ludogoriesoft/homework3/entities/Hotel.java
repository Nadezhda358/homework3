package com.ludogoriesoft.homework3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotels")
public class Hotel {
    @Id
    private int id;
    private String name;
    private String location;
    private int roomsCount;
    private int starsCount;
    private boolean hasSpa;
    private boolean hasPool;
}
