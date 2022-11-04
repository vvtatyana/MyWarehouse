package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.documents.ReportsDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/report")
@Tag(name="Отчеты")
public class ReportsController {
    private final ReportsService reportsService;

    @Autowired
    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Operation(summary = "Общий список товаров")
    @GetMapping(path = "/generalListProducts", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity generalListProducts(@RequestBody(required = false) String nameProduct) {
        try {
            List<ProductDTO> productDTO;
            if (nameProduct == null) productDTO = reportsService.generalListProducts();
            else productDTO= reportsService.generalListProducts(nameProduct);

            return ResponseEntity.ok().body(productDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Остатки товаров на складах")
    @GetMapping(path = "/remnantsGoodsInWarehouses", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity remnantsGoodsInWarehouses(@RequestBody(required = false) String nameWarehouses) {
        try {
            List<ReportsDTO> reportsDTO;
            if (nameWarehouses == null) reportsDTO = reportsService.remnantsGoodsInWarehouses();
            else reportsDTO= reportsService.remnantsGoodsInWarehouses(nameWarehouses);

            return ResponseEntity.ok().body(reportsDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
