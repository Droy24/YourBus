package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

}
