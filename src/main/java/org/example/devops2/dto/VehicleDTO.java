package org.example.devops2.dto;

public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private String type;
    private boolean deleted;

    public VehicleDTO(Long id, String brand, String model, String color, String type, boolean deleted) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.type = type;
        this.deleted = deleted;
    }

    public VehicleDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}