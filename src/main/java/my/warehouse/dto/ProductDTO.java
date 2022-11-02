package my.warehouse.dto;

public class ProductDTO {
    private String article;
    private String name;
    private String priceLastPurchase;
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
}
