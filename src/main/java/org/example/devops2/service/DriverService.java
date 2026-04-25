package org.example.devops2.service;

import org.example.devops2.dto.DriverDTO;

import java.util.List;

public interface DriverService {

    List<DriverDTO> getAllDrivers();

    DriverDTO getDriverById(Long id);

    DriverDTO createDriver(DriverDTO driverDTO);

    DriverDTO updateDriver(Long id, DriverDTO driverDTO);

    void deleteDriver(Long id);
}