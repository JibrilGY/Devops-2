package org.example.devops2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.devops2.dto.VehicleDTO;

import java.util.List;
@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String brand;

    @Column(length = 16)
    private String model;

    @Column(length = 16)
    private String color;

    @Column(length = 16)
    private String type;

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    public VehicleDTO viewAsVehicleDTO() {
        return new VehicleDTO(
                this.id,
                this.brand,
                this.model,
                this.color,
                this.type,
                this.deleted
        );
    }
}