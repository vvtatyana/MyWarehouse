package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.documents.AdmissionDTO;
import my.warehouse.exceptions.DataNotFoundException;
import my.warehouse.serviсe.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/admission")
@Tag(name="Поступление", description="Заводится при поступлении товара")
public class AdmissionController {

    private final AdmissionService admissionService;

    @Autowired
    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @Operation(summary = "Поступление товара", description = "Происходит запись поступившего товара")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity admission(@RequestBody @Valid AdmissionDTO admissionDTO) {
        try {
            admissionService.admission(admissionDTO);
            return ResponseEntity.ok().body("Поступивший товр успешно записан");
        } catch (DataNotFoundException dataNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
        }
    }
}
