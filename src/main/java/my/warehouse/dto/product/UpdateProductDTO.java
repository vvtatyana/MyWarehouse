package my.warehouse.dto.product;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateProductDTO {
    @NotNull(message = "Название склада должено быть заполнено")
    @NotEmpty(message = "Название склада должено быть не пустым")
    private String nameWarehouse;

    @NotNull
    @Valid
    private ProductDTO productDTO;

    @NotNull
    @Valid
    private ProductDTO newProductDTO;

    public UpdateProductDTO() {
    }

    public UpdateProductDTO(String nameWarehouse, ProductDTO productDTO, ProductDTO newProductDTO) {
        this.nameWarehouse = nameWarehouse;
        this.productDTO = productDTO;
        this.newProductDTO = newProductDTO;
    }

    public String getNameWarehouse() {
        return nameWarehouse;
    }

    public void setNameWarehouse(String nameWarehouse) {
        this.nameWarehouse = nameWarehouse;
    }


    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public ProductDTO getNewProductDTO() {
        return newProductDTO;
    }

    public void setNewProductDTO(ProductDTO newProductDTO) {
        this.newProductDTO = newProductDTO;
    }

    @Override
    public String toString() {
        return "nameWarehouse : " + nameWarehouse +
                "\nproductDTO : " + productDTO
                + "\nnewProductDTO : " + newProductDTO;
    }
}
