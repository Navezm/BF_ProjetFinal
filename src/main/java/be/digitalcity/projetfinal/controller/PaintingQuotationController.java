package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;
import be.digitalcity.projetfinal.services.PaintingQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("paintingQuotation")
public class PaintingQuotationController {
    private final PaintingQuotationService service;

    @Autowired
    public PaintingQuotationController(PaintingQuotationService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<PaintingQuotationDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingQuotationDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<PaintingQuotationDTO> insert(@Valid @RequestBody PaintingQuotationForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaintingQuotationDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingQuotationDTO> update(@PathVariable Long id, @Valid @RequestBody PaintingQuotationForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
