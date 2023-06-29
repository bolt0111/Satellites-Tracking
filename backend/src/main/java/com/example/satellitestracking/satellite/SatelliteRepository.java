package com.example.satellitestracking.satellite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, UUID> {
}
