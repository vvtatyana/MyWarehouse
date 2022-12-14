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
public class SaleDTO {
    @NotNull(message = "Номер документа должен быть заполнен")
    @NotEmpty(message = "Номер документа должен быть не пустым")
    String number;

    @NotNull(message = "Данные о складе должены быть заполнены")
    @Valid
    WarehouseDTO warehouse;

    @NotNull(message = "Данные о товарах должены быть заполнены")
    @NotEmpty(message = "Данные о товарах должены быть не пустыми")
    @Valid
    List<ProductDTO> products;

    public SaleDTO() {
    }

    public SaleDTO(String number, WarehouseDTO warehouse, List<ProductDTO> products) {
        this.number = number;
        this.warehouse = warehouse;
        this.products = products;
    }

    @Override
    public String toString() {
        return "number : " + number + "\nwarehouse : " + warehouse + "\nproducts : " + products.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
