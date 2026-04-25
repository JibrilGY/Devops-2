package org.example.devops2.controller;

import org.example.devops2.dto.DriverDTO;
import org.example.devops2.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(
                driverService.createDriver(driverDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DriverDTO> updateDriver(
            @PathVariable Long id,
            @RequestBody DriverDTO driverDTO) {
        return ResponseEntity.ok(
                driverService.updateDriver(id, driverDTO)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}