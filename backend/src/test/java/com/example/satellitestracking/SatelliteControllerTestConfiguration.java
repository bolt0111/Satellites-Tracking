package com.example.satellitestracking;

import com.example.satellitestracking.satellite.SatelliteController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class SatelliteControllerTestConfiguration {
    @Bean
    @Primary
    public SatelliteController productService() {
        return Mockito.mock(SatelliteController.class);
    }
}

