package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.documents.ReportsDTO;
import my.warehouse.dto.warehouse.UpdateWarehouseDTO;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Warehouse;
import my.warehouse.serviсe.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/warehouse")
@Tag(name="Склад")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Operation(summary = "Добавление нового склада")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        warehouseService.add(warehouseDTO);
        return ResponseEntity.ok().body("Данные о новом складе успешно сохранены");
    }

    @Operation(summary = "Изменение данных о складе")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid UpdateWarehouseDTO updateWarehouseDTO) {
        try {
            warehouseService.update(updateWarehouseDTO);
            return ResponseEntity.ok().body("Данные о складе успешно изменены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Удаление данных о складе")
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        try {
            warehouseService.delete(warehouseDTO);
            return ResponseEntity.ok().body("Данные о складе успешно удалены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Просмотр данных о складе")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity get(@RequestBody(required = false) String nameWarehouse) {
        List<WarehouseDTO> warehouses;
        if (nameWarehouse == null) warehouses = warehouseService.getAll();
        else warehouses= warehouseService.get(nameWarehouse);
        return ResponseEntity.ok().body(warehouses);
    }
}
