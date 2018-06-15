package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>  {
	
}
