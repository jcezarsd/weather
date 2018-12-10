package com.example.demo.repositories;

import com.example.demo.models.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRepository extends JpaRepository<Daily, Integer> {
}
