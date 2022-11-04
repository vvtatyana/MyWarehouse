package my.warehouse.dto.documents;

import com.sun.istack.NotNull;

public class NameDTO {
    @NotNull
    private String name;

    public NameDTO(){}

    public NameDTO(String name) {
        this.name = name;
    }
}
