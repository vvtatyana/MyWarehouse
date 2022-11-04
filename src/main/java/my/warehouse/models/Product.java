package my.warehouse.models;

import my.warehouse.dto.product.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product")
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(long idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceLastPurchase() {
        return priceLastPurchase;
    }

    public void setPriceLastPurchase(String priceLastPurchase) {
        this.priceLastPurchase = priceLastPurchase;
    }

    public String getPriceLastSale() {
        return priceLastSale;
    }

    public void setPriceLastSale(String priceLastSale) {
        this.priceLastSale = priceLastSale;
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
