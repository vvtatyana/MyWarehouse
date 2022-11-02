package my.warehouse.dto;

public class WarehouseDTO {
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
}
