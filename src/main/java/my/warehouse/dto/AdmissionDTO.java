package my.warehouse.dto;

import java.util.List;

public class AdmissionDTO {
    String number;
    WarehouseDTO warehouse;
    List<ProductDTO> products;

    public AdmissionDTO() {
    }

    public AdmissionDTO(String number, WarehouseDTO warehouse, List<ProductDTO> products) {
        this.number = number;
        this.warehouse = warehouse;
        this.products = products;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public WarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
