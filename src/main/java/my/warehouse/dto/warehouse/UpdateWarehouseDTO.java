package my.warehouse.dto.warehouse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UpdateWarehouseDTO {
    @NotNull
    @Valid
    private WarehouseDTO warehouseDTO;

    @NotNull
    @Valid
    private WarehouseDTO newWarehouseDTO;

    public UpdateWarehouseDTO(){}

    public UpdateWarehouseDTO(WarehouseDTO warehouseDTO, WarehouseDTO newWarehouseDTO) {
        this.warehouseDTO = warehouseDTO;
        this.newWarehouseDTO = newWarehouseDTO;
    }

    public WarehouseDTO getWarehouseDTO() {
        return warehouseDTO;
    }

    public void setWarehouseDTO(WarehouseDTO warehouseDTO) {
        this.warehouseDTO = warehouseDTO;
    }

    public WarehouseDTO getNewWarehouseDTO() {
        return newWarehouseDTO;
    }

    public void setNewWarehouseDTO(WarehouseDTO newWarehouseDTO) {
        this.newWarehouseDTO = newWarehouseDTO;
    }

    @Override
    public String toString() {
        return "warehouseDTO : " + warehouseDTO +
                "\newWarehouseDTO : " + newWarehouseDTO;
    }
}
