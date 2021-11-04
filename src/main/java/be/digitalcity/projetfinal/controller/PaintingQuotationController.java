package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingQuotationDTO;
import be.digitalcity.projetfinal.models.form.PaintingQuotationForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.services.PaintingQuotationService;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.time.LocalDate;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PaintingQuotationDTO>> getByUser(@PathVariable Long id){
        return ResponseEntity.ok(service.findByUser(id));
    }

    @GetMapping("/status")
    public ResponseEntity<List<PaintingQuotationDTO>> getByStatus(@Valid @RequestBody StatusForm status){
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/date")
    public ResponseEntity<List<PaintingQuotationDTO>> getByOrdeDate(@Valid @RequestBody DateForm date){
        return ResponseEntity.ok(service.findByOrderDate(date));
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
