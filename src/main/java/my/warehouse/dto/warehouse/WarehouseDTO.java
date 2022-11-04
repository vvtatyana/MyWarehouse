package my.warehouse.dto.warehouse;

import my.warehouse.models.Warehouse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WarehouseDTO {
    @NotNull(message = "Название склада должно быть задано")
    @NotEmpty(message = "Название склада должно быть не пустым")
    private String name;

    public WarehouseDTO() {
    }

    public WarehouseDTO(String name) {
        this.name = name;
    }

    public WarehouseDTO(Warehouse warehouse) {
        this.name = warehouse.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name : " + name;
    }
}
