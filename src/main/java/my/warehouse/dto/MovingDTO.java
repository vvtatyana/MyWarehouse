package my.warehouse.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class MovingDTO implements IDTO {

    @NotNull(message = "Номер документа должен быть заполнен")
    @NotEmpty(message = "Номер документа должен быть не пустым")
    String number;

    @NotNull(message = "Данные о складе 1 должены быть заполнены")
    @Valid
    WarehouseDTO warehouseOne;

    @NotNull(message = "Данные о складе 2 должены быть заполнены")
    @Valid
    WarehouseDTO warehouseTwo;

    @NotNull(message = "Данные о товарах должены быть заполнены")
    @NotEmpty(message = "Данные о товарах должены быть не пустыми")
    @Valid
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

    @Override
    public String toString() {
        return "number : " + number + "\nwarehouseOne : "  + warehouseOne + "\nwarehouseTwo : " + warehouseTwo + "\nproducts : " + products.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
