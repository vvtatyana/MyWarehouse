package my.warehouse.dto.documents;

public class ReportsDTO {
    private String article;
    private String name;
    private int remains;

    public ReportsDTO() {
    }

    public ReportsDTO(String article, String name, int remains) {
        this.article = article;
        this.name = name;
        this.remains = remains;
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

    public int getRemains() {
        return remains;
    }

    public void setRemains(int remains) {
        this.remains = remains;
    }

    @Override
    public String toString() {
        return "article : " + article + "\nname : " + name + "\nremains : " + remains;
    }

}
