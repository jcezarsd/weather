package com.example.demo.repositories;

import com.example.demo.models.Hourly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HourlyRepository extends JpaRepository<Hourly, Integer> {

	@Query("SELECT h FROM Hourly h WHERE h.hour >= ?1 and h.hour < ?2")
	List<Hourly> getAllBetweenDates(Date start, Date end);

}
