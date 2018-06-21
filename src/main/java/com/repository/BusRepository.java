package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Bus;
import com.entity.Station;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
	/*
	 * @Query("Select b from Bus b join b.stops st where :source in st.stationId and :destination in st.stationId"
	 * ) List<Bus> findBusStops( @Param("source") int source, @Param("destination")
	 * int destination);
	 */
	// List<Bus> findAllBySourceAndDestination(Station source, Station destination);

	// @Query("select u from User u where source in u.inbween.id AND source in
	// u.inbetween.id")
	// List<Bus> findAllByInbetweenContaining(int source, int destination);
}