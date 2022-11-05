package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.product.FullProductDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.models.Product;
import my.warehouse.serviсe.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/product")
@Tag(name="Товар")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Добавление нового товара")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity add (@RequestBody @Valid FullProductDTO newProductDTO) {
        try {
            Product product = productService.add(newProductDTO);
            return ResponseEntity.ok().body(product);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось сохранить данные товара");
        }
    }

    @Operation(summary = "Изменение данных о товаре")
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid FullProductDTO updateProductDTO) {
        try {
            productService.update(id, updateProductDTO);
            return ResponseEntity.ok().body("Данные о товаре успешно изменены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Удаление данных о товаре")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            productService.delete(id);
            return ResponseEntity.ok().body("Данные о товаре успешно удалены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Просмотр данных о товаре")
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable Long id) {
        try {
            ProductDTO productDTO = productService.get(id);
            return ResponseEntity.ok().body(productDTO);
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
