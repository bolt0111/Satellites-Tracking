package com.example.satellitestracking.satellite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/satellites")
@CrossOrigin(origins = "http://localhost:3000")
public class SatelliteController {

    private final SatelliteService satelliteService;

    @Autowired
    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @GetMapping
    public List<Satellite> getSatellites() {
        return satelliteService.getSatellites();
    }

    @PostMapping
    public ResponseEntity<Satellite> registerNewSatellite(@RequestBody Satellite satellite) {
        System.out.println(satellite);
        return satelliteService.addNewSatellite(satellite);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Satellite> updateSatellite(
            @PathVariable("id") UUID id,
            @RequestBody Satellite satellite
    ) {
        return satelliteService.updateSatellite(id, satellite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Satellite> deleteSatellite(
            @PathVariable("id") UUID id
    ) {
        return satelliteService.deleteSatellite(id);
    }
}
