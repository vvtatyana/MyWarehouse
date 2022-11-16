package my.warehouse.models;

import lombok.Getter;
import lombok.Setter;
import my.warehouse.dto.product.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "warehouse")
    private long idWarehouse;

    @NotNull(message = "Артикул должен быть заполнено")
    @NotEmpty(message = "Артикул должен быть не пустой")
    @Column(name = "article")
    private String article;

    @NotNull(message = "Название товара должно быть заполнено")
    @NotEmpty(message = "Название товара должно быть не пустым")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Цена последней закупки должна быть заполнена")
    @NotEmpty(message = "Цена последней закупки должна быть не пустой")
    @Column(name = "price_last_purchase")
    private String priceLastPurchase;

    @NotNull(message = "Цена последней продажи должна быть заполнена")
    @NotEmpty(message = "Цена последней продажи должена быть не пустой")
    @Column(name = "price_last_sale")
    private String priceLastSale;

    public Product() {
    }

    public Product(long idWarehouse, String article, String name, String priceLastPurchase, String priceLastSale) {
        this.idWarehouse = idWarehouse;
        this.article = article;
        this.name = name;
        this.priceLastPurchase = priceLastPurchase;
        this.priceLastSale = priceLastSale;
    }

    public Product(long idWarehouse, ProductDTO productDTO) {
        this.idWarehouse = idWarehouse;
        this.article = productDTO.getArticle();
        this.name = productDTO.getName();
        this.priceLastPurchase = productDTO.getPriceLastPurchase();
        this.priceLastSale = productDTO.getPriceLastSale();
    }

    @Override
    public String toString() {
        return "warehouse : " + idWarehouse +
                "\narticle : " + article +
                "\nname : " + name +
                "\npriceLastPurchase : " + priceLastPurchase +
                "\npriceLastSale : " + priceLastSale;
    }
}
