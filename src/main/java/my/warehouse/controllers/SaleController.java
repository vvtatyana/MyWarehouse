package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.documents.SaleDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sale")
@Tag(name="Продажа", description="Заводится при продаже товара")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @Operation(summary = "Продажа товара", description = "Происходит продажа товара")
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity sale(@RequestBody @Valid SaleDTO saleDTO) {
        try {
            saleService.sale(saleDTO);
            return ResponseEntity.ok().body("Товар успешно продан");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
