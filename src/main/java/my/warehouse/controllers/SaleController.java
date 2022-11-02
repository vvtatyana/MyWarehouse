package my.warehouse.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.ProductDTO;
import my.warehouse.dto.SaleDTO;
import my.warehouse.servise.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> sale(@RequestBody SaleDTO saleDTO) {
        saleService.sale(saleDTO);
        return ResponseEntity.ok(saleService.products(saleDTO.getWarehouse()));
    }
}
