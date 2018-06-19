package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

}
