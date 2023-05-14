package com.ludogoriesoft.homework3.repositories;

import com.ludogoriesoft.homework3.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
