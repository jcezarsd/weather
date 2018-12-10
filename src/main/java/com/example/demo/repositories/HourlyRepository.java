package com.example.demo.repositories;

import com.example.demo.models.Hourly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyRepository extends JpaRepository<Hourly, Integer> {
}
