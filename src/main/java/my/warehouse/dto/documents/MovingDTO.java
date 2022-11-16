package my.warehouse.dto.documents;

import lombok.Getter;
import lombok.Setter;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.dto.warehouse.WarehouseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MovingDTO{

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

    @Override
    public String toString() {
        return "number : " + number + "\nwarehouseOne : "  + warehouseOne + "\nwarehouseTwo : " + warehouseTwo + "\nproducts : " + products.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
