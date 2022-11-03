package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.MovingDTO;
import my.warehouse.dto.validate.Validate;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.MovingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/moving")
@Tag(name="Перемещение", description="Заводится при перемещении товара между складами")
public class MovingController {
    private final MovingService movingService;
    private final Validate validate;

    @Autowired
    public MovingController(MovingService movingService, Validate validate) {
        this.movingService = movingService;
        this.validate = validate;
    }

    @Operation(summary = "Перемещение товара", description = "Происходит перемещение товара между складами")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity moving(@RequestBody MovingDTO movingDTO) {
        List<String> error = validate.validate(movingDTO);
        if (!error.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.stream().map(Object::toString).collect(Collectors.joining("\n")));

        try {
            movingService.moving(movingDTO);
            return ResponseEntity.ok(movingService.products(movingDTO.getWarehouseTwo()));
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
