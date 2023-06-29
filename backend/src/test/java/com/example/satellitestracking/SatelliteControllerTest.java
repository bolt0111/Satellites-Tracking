package com.example.satellitestracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.satellitestracking.satellite.Satellite;
import com.example.satellitestracking.satellite.SatelliteService;
import com.example.satellitestracking.satellite.SatelliteController;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = SatelliteControllerTestConfiguration.class)
public class SatelliteControllerTest {

	@Mock
	private SatelliteService satelliteService;

	private SatelliteController satelliteController;

	@BeforeEach
	public void setup(){
		satelliteController = new SatelliteController(satelliteService);
	}

	@Test
	public void testGetSatellites() {
		Satellite satellite1 = new Satellite(UUID.fromString("f5173b67-5a72-4fbe-9a83-5713e1030df4"), "Satellite 1", "Owner 1", 12.1f, 67.590f);
		Satellite satellite2 = new Satellite(UUID.fromString("7fa751b7-c592-4984-84b8-ec693ff921bb"), "Satellite 2", "Owner 2", 32.5f, 12.75f);
		List<Satellite> expectedSatellites = Arrays.asList(satellite1, satellite2);

		when(satelliteService.getSatellites()).thenReturn(expectedSatellites);

		List<Satellite> actualSatellites = satelliteController.getSatellites();

		assertEquals(expectedSatellites, actualSatellites);
		verify(satelliteService, times(1)).getSatellites();
	}

	@Test
	public void testRegisterNewSatellite() {
		Satellite satellite = new Satellite("Satellite 1", "Owner 1", 12.812f, 64.91f);
		Satellite addedSatellite = new Satellite(UUID.fromString("f5173b67-5a72-4fbe-9a83-5713e1030df4"), "Satellite 1", "Owner 1", 12.812f, 64.91f);
		ResponseEntity<Satellite> expectedResponse = new ResponseEntity<>(addedSatellite, HttpStatus.CREATED);

		when(satelliteService.addNewSatellite(satellite)).thenReturn(expectedResponse);

		ResponseEntity<Satellite> actualResponse = satelliteController.registerNewSatellite(satellite);

		assertEquals(expectedResponse, actualResponse);
		verify(satelliteService, times(1)).addNewSatellite(satellite);
	}

	@Test
	public void testUpdateSatellite() {
		UUID satelliteId = UUID.fromString("f5173b67-5a72-4fbe-9a83-5713e1030df4");
		Satellite satellite = new Satellite(satelliteId, "Updated Satellite", "Updated Owner", 0.0f, 0.0f);
		ResponseEntity<Satellite> expectedResponse = new ResponseEntity<>(satellite, HttpStatus.OK);

		when(satelliteService.updateSatellite(satelliteId, satellite)).thenReturn(expectedResponse);

		ResponseEntity<Satellite> actualResponse = satelliteController.updateSatellite(satelliteId, satellite);

		assertEquals(expectedResponse, actualResponse);
		verify(satelliteService, times(1)).updateSatellite(satelliteId, satellite);
	}

	@Test
	public void testDeleteSatellite() {
		UUID satelliteId = UUID.fromString("f5173b67-5a72-4fbe-9a83-5713e1030df4");
		Satellite satellite = new Satellite(satelliteId, "Satellite 1", "Owner 1", 21.5f, 61.20f);
		ResponseEntity<Satellite> expectedResponse = new ResponseEntity<>(satellite, HttpStatus.OK);

		when(satelliteService.deleteSatellite(satelliteId)).thenReturn(expectedResponse);

		ResponseEntity<Satellite> actualResponse = satelliteController.deleteSatellite(satelliteId);

		assertEquals(expectedResponse, actualResponse);
		verify(satelliteService, times(1)).deleteSatellite(satelliteId);
	}
}
