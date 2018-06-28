package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Cancellation;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Long>{

}
