package org.example.devops2.service;

import org.example.devops2.dto.VehicleDTO;
import org.example.devops2.exception.ErrorMessages;
import org.example.devops2.exception.ResourceNotFoundException;
import org.example.devops2.model.Vehicle;
import org.example.devops2.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .filter(vehicle -> !vehicle.isDeleted())
                .map(Vehicle::viewAsVehicleDTO)
                .toList();
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .filter(v -> !v.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_VEHICLE_NOT_FOUND + ": " + id
                        )
                );

        return vehicle.viewAsVehicleDTO();
    }

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(vehicleDTO.getBrand());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setColor(vehicleDTO.getColor());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setDeleted(false);

        return vehicleRepository.save(vehicle).viewAsVehicleDTO();
    }

    @Override
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {

        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_VEHICLE_NOT_FOUND + ": " + id
                        )
                );

        existingVehicle.setBrand(vehicleDTO.getBrand());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setColor(vehicleDTO.getColor());
        existingVehicle.setType(vehicleDTO.getType());

        return vehicleRepository.save(existingVehicle).viewAsVehicleDTO();
    }

    @Override
    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_VEHICLE_NOT_FOUND + ": " + id
                        )
                );

        // Soft delete
        vehicle.setDeleted(true);
        vehicleRepository.save(vehicle);
    }
}