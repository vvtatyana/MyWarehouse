package my.warehouse.dto.product;

import lombok.Getter;
import lombok.Setter;
import my.warehouse.models.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDTO {
    @NotNull(message = "Артикул должен быть заполнено")
    @NotEmpty(message = "Артикул должен быть не пустой")
    private String article;

    @NotNull(message = "Название товара должно быть заполнено")
    @NotEmpty(message = "Название товара должно быть не пустым")
    private String name;

    @NotNull(message = "Цена последней закупки должна быть заполнена")
    @NotEmpty(message = "Цена последней закупки должна быть не пустая")
    private String priceLastPurchase;

    @NotNull(message = "Цена последней продажи должна быть заполнена")
    @NotEmpty(message = "Цена последней продажи должена быть не пустой")
    private String priceLastSale;

    public ProductDTO() {
    }

    public ProductDTO(String article, String name, String priceLastPurchase, String priceLastSale) {
        this.article = article;
        this.name = name;
        this.priceLastPurchase = priceLastPurchase;
        this.priceLastSale = priceLastSale;
    }

    public ProductDTO(Product product) {
        this.article = product.getArticle();
        this.name = product.getName();
        this.priceLastPurchase = product.getPriceLastPurchase();
        this.priceLastSale = product.getPriceLastSale();
    }

    @Override
    public String toString() {
        return "article : " + article
                + "\nname : " + name
                + "\npriceLastPurchase : " + priceLastPurchase
                + "\npriceLastSale : " + priceLastSale;
    }
}
