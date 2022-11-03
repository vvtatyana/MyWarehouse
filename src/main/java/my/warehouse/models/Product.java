package my.warehouse.models;

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

    @NotEmpty(message = "Warehouse id must not be empty")
    @Column(name = "warehouse")
    private long idWarehouse;

    @NotNull(message = "Артикул должен быть заполнено")
    @NotEmpty(message = "Артикул должен быть заполнен")
    @Column(name = "article")
    private String article;

    @NotNull(message = "Название товара должно быть заполнено")
    @NotEmpty(message = "Название товара должно быть заполнено")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Цена последней закупки должна быть заполнена")
    @NotEmpty(message = "Цена последней закупки должна быть заполнена")
    @Column(name = "priceLastPurchase")
    private String priceLastPurchase;

    @NotNull(message = "Цена последней продажи должна быть заполнена")
    @NotEmpty(message = "Цена последней продажи должена быть не пустой")
    @Column(name = "priceLastSale")
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
        return "article : " + article + "\nname : " + name + "\npriceLastPurchase : " + priceLastPurchase + "\npriceLastSale : " + priceLastSale;
    }
}
