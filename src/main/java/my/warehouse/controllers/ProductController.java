package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.product.FullProductDTO;
import my.warehouse.dto.product.ProductDTO;
import my.warehouse.dto.product.UpdateProductDTO;
import my.warehouse.exceptions.DataNotFoundException;
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
            productService.add(newProductDTO);
            return ResponseEntity.ok().body("Данные о новом товаре успешно сохранены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Изменение данных о товаре")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody @Valid UpdateProductDTO updateProductDTO) {

        try {
            productService.update(updateProductDTO);
            return ResponseEntity.ok().body("Данные о товаре успешно изменены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Удаление данных о товаре")
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody @Valid FullProductDTO newProductDTO) {
        try {
            productService.delete(newProductDTO);
            return ResponseEntity.ok().body("Данные о товаре успешно удалены");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }

    @Operation(summary = "Просмотр данных о товаре")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity get(@RequestBody(required = false) String nameProduct) {
        List<ProductDTO> productDTO;
        if (nameProduct == null) productDTO = productService.getAll();
        else productDTO = productService.get(nameProduct);
        return ResponseEntity.ok().body(productDTO);
    }
}
