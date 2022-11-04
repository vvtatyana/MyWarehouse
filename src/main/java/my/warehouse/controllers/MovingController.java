package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.documents.MovingDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.MovingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/moving")
@Tag(name="Перемещение", description="Заводится при перемещении товара между складами")
public class MovingController {
    private final MovingService movingService;

    @Autowired
    public MovingController(MovingService movingService) {
        this.movingService = movingService;
    }

    @Operation(summary = "Перемещение товара", description = "Происходит перемещение товара между складами")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity moving(@RequestBody @Valid MovingDTO movingDTO) {
        try {
            movingService.moving(movingDTO);
            return ResponseEntity.ok().body("Товар успешно перемещен");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
