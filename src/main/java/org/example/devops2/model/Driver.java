package org.example.devops2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.devops2.dto.DriverDTO;

import java.util.List;
@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String name;

    @Column(length = 32)
    private String address;

    @Column(length = 16)
    private String phone;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    public DriverDTO viewAsDriverDTO() {
        return new DriverDTO(
                this.id,
                this.name,
                this.address,
                this.phone,
                this.isDeleted
        );
    }
}