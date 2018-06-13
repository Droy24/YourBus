package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>  {
	
}
