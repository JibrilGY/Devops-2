package org.example.devops2.service;

import org.example.devops2.dto.AssignmentDTO;
import org.example.devops2.exception.ErrorMessages;
import org.example.devops2.exception.ResourceNotFoundException;
import org.example.devops2.model.Assignment;
import org.example.devops2.model.Driver;
import org.example.devops2.model.Vehicle;
import org.example.devops2.repository.AssignmentRepository;
import org.example.devops2.repository.DriverRepository;
import org.example.devops2.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 DriverRepository driverRepository,
                                 VehicleRepository vehicleRepository) {
        this.assignmentRepository = assignmentRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<AssignmentDTO> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public AssignmentDTO getAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .filter(a -> !a.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_ASSIGNMENT_NOT_FOUND + ": " + id
                        )
                );

        return convertToDTO(assignment);
    }

    @Override
    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {

        Driver driver = driverRepository.findById(assignmentDTO.getDriverId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Driver not found with ID: " + assignmentDTO.getDriverId()
                        )
                );

        Vehicle vehicle = vehicleRepository.findById(assignmentDTO.getVehicleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with ID: " + assignmentDTO.getVehicleId()
                        )
                );

        Assignment assignment = new Assignment();
        assignment.setAssignedDate(assignmentDTO.getAssignedDate());
        assignment.setReturnDate(assignmentDTO.getReturnDate());
        assignment.setDriver(driver);
        assignment.setVehicle(vehicle);

        return convertToDTO(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDTO updateAssignment(Long id, AssignmentDTO assignmentDTO) {

        Assignment existing = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_ASSIGNMENT_NOT_FOUND + ": " + id
                        )
                );

        Driver driver = driverRepository.findById(assignmentDTO.getDriverId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Driver not found with ID: " + assignmentDTO.getDriverId()
                        )
                );

        Vehicle vehicle = vehicleRepository.findById(assignmentDTO.getVehicleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with ID: " + assignmentDTO.getVehicleId()
                        )
                );

        existing.setAssignedDate(assignmentDTO.getAssignedDate());
        existing.setReturnDate(assignmentDTO.getReturnDate());
        existing.setDriver(driver);
        existing.setVehicle(vehicle);

        return convertToDTO(assignmentRepository.save(existing));
    }

    @Override
    public void deleteAssignment(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorMessages.ERROR_ASSIGNMENT_NOT_FOUND + ": " + id
                        )
                );

        assignmentRepository.delete(assignment);
    }

    private AssignmentDTO convertToDTO(Assignment assignment) {
        return new AssignmentDTO(
                assignment.getId(),
                assignment.getAssignedDate(),
                assignment.getReturnDate(),
                assignment.getDriver().getId(),
                assignment.getVehicle().getId()
        );
    }
}