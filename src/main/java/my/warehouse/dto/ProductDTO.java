package my.warehouse.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductDTO implements IDTO {
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
