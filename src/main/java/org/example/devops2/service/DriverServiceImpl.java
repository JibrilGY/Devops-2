package org.example.devops2.service;

import org.example.devops2.exception.ErrorMessages;
import org.example.devops2.exception.ResourceNotFoundException;
import org.example.devops2.model.Driver;
import org.example.devops2.repository.DriverRepository;
import org.example.devops2.dto.DriverDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll()
                .stream()
                .filter(driver -> !driver.isDeleted())
                .map(Driver::viewAsDriverDTO)
                .toList();
    }

    @Override
    public DriverDTO getDriverById(Long id) {
        Driver driver = driverRepository.findById(id)
                .filter(d -> !d.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessages.ERROR_DRIVER_NOT_FOUND + ": " + id)
                );

        return driver.viewAsDriverDTO();
    }

    @Override
    public DriverDTO createDriver(DriverDTO driverDTO) {

        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setAddress(driverDTO.getAddress());
        driver.setPhone(driverDTO.getPhone());
        driver.setDeleted(false);

        return driverRepository.save(driver).viewAsDriverDTO();
    }

    @Override
    public DriverDTO updateDriver(Long id, DriverDTO driverDTO) {

        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessages.ERROR_DRIVER_NOT_FOUND + ": " + id)
                );

        existingDriver.setName(driverDTO.getName());
        existingDriver.setAddress(driverDTO.getAddress());
        existingDriver.setPhone(driverDTO.getPhone());

        return driverRepository.save(existingDriver).viewAsDriverDTO();
    }

    @Override
    public void deleteDriver(Long id) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(ErrorMessages.ERROR_DRIVER_NOT_FOUND + ": " + id)
                );

        // Soft delete
        driver.setDeleted(true);
        driverRepository.save(driver);
    }
}