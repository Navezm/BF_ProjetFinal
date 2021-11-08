package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingPurchaseDTO;
import be.digitalcity.projetfinal.models.form.PaintingPurchaseForm;
import be.digitalcity.projetfinal.models.form.typeForm.DateForm;
import be.digitalcity.projetfinal.models.form.typeForm.StatusForm;
import be.digitalcity.projetfinal.services.PaintingPurchaseService;
import be.digitalcity.projetfinal.util.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("paintingPurchase")
public class PaintingPurchaseController {
    private final PaintingPurchaseService service;

    @Autowired
    public PaintingPurchaseController(PaintingPurchaseService service) {
        this.service = service;
    }

    @GetMapping({""})
    public ResponseEntity<List<PaintingPurchaseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingPurchaseDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<PaintingPurchaseDTO>> getByUser(@PathVariable Long id){
//        return ResponseEntity.ok(service.findByUser(id));
//    }

    @GetMapping("/status")
    public ResponseEntity<List<PaintingPurchaseDTO>> getByStatus(@Valid @RequestBody StatusForm status){
        return ResponseEntity.ok(service.findByStatus(status));
    }

    @GetMapping("/date")
    public ResponseEntity<List<PaintingPurchaseDTO>> getByOrderDate(@Valid @RequestBody DateForm date){
        return ResponseEntity.ok(service.findByOrderDate(date));
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
