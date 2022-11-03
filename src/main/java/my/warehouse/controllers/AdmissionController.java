package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.AdmissionDTO;
import my.warehouse.dto.validate.Validate;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/admission")
@Tag(name="Поступление", description="Заводится при поступлении товара")
public class AdmissionController {

    private final AdmissionService admissionService;
    private final Validate validate;

    @Autowired
    public AdmissionController(AdmissionService admissionService, Validate validate) {
        this.admissionService = admissionService;
        this.validate = validate;
    }

    @Operation(summary = "Поступление товара", description = "Происходит запись поступившего товара")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity admission(@RequestBody AdmissionDTO admissionDTO) {
        List<String> error = validate.validate(admissionDTO);
        if (!error.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.stream().map(Object::toString).collect(Collectors.joining("\n")));

            try {
            admissionService.admission(admissionDTO);
            return ResponseEntity.ok(admissionService.products(admissionDTO.getWarehouse()));
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
