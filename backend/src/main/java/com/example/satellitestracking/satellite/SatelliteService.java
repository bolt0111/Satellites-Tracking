package com.example.satellitestracking.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;
    @Autowired
    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }
    public List<Satellite> getSatellites() {
        return satelliteRepository.findAll();
    }

    public ResponseEntity<Satellite> addNewSatellite(Satellite satellite) {
        Satellite addedSatellite = satelliteRepository.save(satellite);
        return new ResponseEntity<>(addedSatellite, HttpStatus.CREATED);
    }

    public ResponseEntity<Satellite> updateSatellite(
            UUID id,
            Satellite satellite
    ) {
        Optional<Satellite> optionalSatellite = satelliteRepository.findById(id);
        if (optionalSatellite.isPresent()) {
            Satellite existingSatellite = optionalSatellite.get();
            existingSatellite.setName(satellite.getName());
            existingSatellite.setOwner(satellite.getOwner());
            existingSatellite.setLatitude(satellite.getLatitude());
            existingSatellite.setLongitude(satellite.getLongitude());
            satelliteRepository.save(existingSatellite);
            return new ResponseEntity<>(existingSatellite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Satellite> deleteSatellite(
            UUID id
    ) {
        Optional<Satellite> optionalSatellite = satelliteRepository.findById(id);
        if(optionalSatellite.isPresent()) {
            satelliteRepository.delete(optionalSatellite.get());
            return new ResponseEntity<>(optionalSatellite.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
