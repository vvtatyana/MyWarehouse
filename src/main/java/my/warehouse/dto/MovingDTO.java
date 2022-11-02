package my.warehouse.dto;

import java.util.List;

public class MovingDTO {

    String number;
    WarehouseDTO warehouseOne;
    WarehouseDTO warehouseTwo;
    List<ProductDTO> products;

    public MovingDTO() {
    }

    public MovingDTO(String number, WarehouseDTO warehouseOne, WarehouseDTO warehouseTwo, List<ProductDTO> products) {
        this.number = number;
        this.warehouseOne = warehouseOne;
        this.warehouseTwo = warehouseTwo;
        this.products = products;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public WarehouseDTO getWarehouseOne() {
        return warehouseOne;
    }

    public void setWarehouseOne(WarehouseDTO warehouseOne) {
        this.warehouseOne = warehouseOne;
    }

    public WarehouseDTO getWarehouseTwo() {
        return warehouseTwo;
    }

    public void setWarehouseTwo(WarehouseDTO warehouseTwo) {
        this.warehouseTwo = warehouseTwo;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
