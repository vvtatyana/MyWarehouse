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
import java.util.Optional;

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
    @GetMapping( "/generalListProducts")
    public ResponseEntity generalListProducts(@RequestParam(required = false) Optional<String> nameProduct) {
        try {
            List<ProductDTO> productDTO;
            if (nameProduct.isPresent())  productDTO = reportsService.generalListProducts(nameProduct.get());
            else productDTO= reportsService.generalListProducts();

            return ResponseEntity.ok().body(productDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Остатки товаров на складах")
    @GetMapping("/remnantsGoodsInWarehouses")
    public ResponseEntity remnantsGoodsInWarehouses(@RequestParam(required = false) Optional<String> nameWarehouses) {
        try {
            List<ReportsDTO> reportsDTO;
            if (nameWarehouses.isPresent()) reportsDTO = reportsService.remnantsGoodsInWarehouses(nameWarehouses.get());
            else reportsDTO= reportsService.remnantsGoodsInWarehouses();

            return ResponseEntity.ok().body(reportsDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
