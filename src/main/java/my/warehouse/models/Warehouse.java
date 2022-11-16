package my.warehouse.models;

import lombok.Getter;
import lombok.Setter;
import my.warehouse.dto.warehouse.WarehouseDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class Warehouse implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Название склада должно быть задано")
    @NotEmpty(message = "Название склада должно быть не пустым")
    @Column(name = "name")
    private String name;

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(WarehouseDTO warehouseDTO) {
        this.name = warehouseDTO.getName();
    }

    @Override
    public String toString() {
        return "name : " + name;
    }
}
