package com.example.demo.repositories;

import com.example.demo.models.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface DailyRepository extends JpaRepository<Daily, Integer> {

	@Query("SELECT d FROM Daily d where d.day > ?1 ORDER BY d.day DESC")
	List<Daily> findLastSevenDays(Date date);

}
