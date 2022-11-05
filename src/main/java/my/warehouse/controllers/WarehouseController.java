package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.warehouse.WarehouseDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import my.warehouse.models.Warehouse;
import my.warehouse.serviсe.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @Operation(summary = "Добавление данных нового склада")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody @Valid WarehouseDTO warehouseDTO) {
        try {
            Warehouse warehouse = warehouseService.add(warehouseDTO);
            return ResponseEntity.ok().body(warehouse);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось сохранить данные склада");
        }
    }

    @Operation(summary = "Изменение данных склада")
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid  WarehouseDTO warehouseDTO) {
        try {
            warehouseService.update(id, warehouseDTO);
            return ResponseEntity.ok().body("Данные о складе успешно изменены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Удаление данных склада")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            warehouseService.delete(id);
            return ResponseEntity.ok().body("Данные о складе успешно удалены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Просмотр данных о складе")
    @GetMapping(path = "/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        try {
            WarehouseDTO warehouseDTO = warehouseService.get(id);
            return ResponseEntity.ok().body(warehouseDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
