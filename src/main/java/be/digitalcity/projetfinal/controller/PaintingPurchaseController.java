package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;
import be.digitalcity.projetfinal.services.PaintingPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("paintingPurchase")
public class PaintingPurchaseController {
    private final PaintingPurchaseService service;

    @Autowired
    public PaintingPurchaseController(PaintingPurchaseService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<PaintingPurchaseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingPurchaseDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<PaintingPurchaseDTO> insert(@Valid @RequestBody PaintingPurchaseForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaintingPurchaseDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingPurchaseDTO> update(@PathVariable Long id, @Valid @RequestBody PaintingPurchaseForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
