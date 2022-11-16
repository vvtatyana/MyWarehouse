package my.warehouse.dto.warehouse;

import lombok.Getter;
import lombok.Setter;
import my.warehouse.models.Warehouse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "name : " + name;
    }
}
