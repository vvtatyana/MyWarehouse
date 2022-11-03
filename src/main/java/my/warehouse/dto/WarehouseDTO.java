package my.warehouse.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WarehouseDTO implements IDTO {
    @NotNull(message = "Название склада должно быть задано")
    @NotEmpty(message = "Название склада должно быть не пустым")
    private String name;

    public WarehouseDTO() {
    }

    public WarehouseDTO(String name) {
        this.name = name;
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
