package com.ludogoriesoft.homework3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 60)
    private String name;
    @Size(min = 2, max = 2000)
    private String location;
    @Min(1)
    private int roomsCount;
    @Min(1)
    @Max(5)
    private int starsCount;
    private boolean hasSpa;
    private boolean hasPool;
}
