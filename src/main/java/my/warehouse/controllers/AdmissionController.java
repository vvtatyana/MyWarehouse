package my.warehouse.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.warehouse.dto.AdmissionDTO;
import my.warehouse.dto.ProductDTO;
import my.warehouse.servise.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> admission(@RequestBody AdmissionDTO admissionDTO) {
        admissionService.admission(admissionDTO);
        return ResponseEntity.ok(admissionService.products(admissionDTO.getWarehouse()));
    }
}
