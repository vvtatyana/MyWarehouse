package my.warehouse.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.SaleDTO;
import my.warehouse.dto.validate.Validate;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sale")
@Tag(name="Продажа", description="Заводится при продаже товара")
public class SaleController {

    private final SaleService saleService;
    private final Validate validate;

    @Autowired
    public SaleController(SaleService saleService, Validate validate) {
        this.saleService = saleService;
        this.validate = validate;
    }

    @Operation(summary = "Продажа товара", description = "Происходит продажа товара")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity sale(@RequestBody SaleDTO saleDTO) {
        List<String> error = validate.validate(saleDTO);
        if (!error.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.stream().map(Object::toString).collect(Collectors.joining("\n")));

        try {
            saleService.sale(saleDTO);
            return ResponseEntity.ok(saleService.products(saleDTO.getWarehouse()));
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
