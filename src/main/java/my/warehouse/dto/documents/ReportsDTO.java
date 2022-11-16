package my.warehouse.dto.documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "article : " + article + "\nname : " + name + "\nremains : " + remains;
    }

}
