package my.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FullProductDTO {
    @NotNull(message = "Название склада должено быть заполнено")
    @NotEmpty(message = "Название склада должено быть не пустым")
    private String nameWarehouse;

    @NotNull
    @Valid
    private ProductDTO productDTO;

    public FullProductDTO() {
    }

    public FullProductDTO(String nameWarehouse, ProductDTO productDTO) {
        this.nameWarehouse = nameWarehouse;
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "nameWarehouse : " + nameWarehouse +
                "\nproductDTO : " + productDTO;
    }
}
