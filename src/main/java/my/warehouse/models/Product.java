package my.warehouse.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "warehouse")
    private long idWarehouse;

    @Column(name = "article")
    private String article;

    @Column(name = "name")
    private String name;

    @Column(name = "priceLastPurchase")
    private String priceLastPurchase;

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
        return article + ", " + name + ", " + priceLastPurchase + ", " + priceLastSale;
    }


}
