package org.example.devops2.dto;

import java.time.LocalDateTime;

public class AssignmentDTO {

    private long id;
    private LocalDateTime assignedDate;
    private LocalDateTime returnDate;
    private long driverId;
    private long vehicleId;

    public AssignmentDTO() {}

    public AssignmentDTO(long id, LocalDateTime assignedDate,
                         LocalDateTime returnDate,
                         long driverId, long vehicleId) {
        this.id = id;
        this.assignedDate = assignedDate;
        this.returnDate = returnDate;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}