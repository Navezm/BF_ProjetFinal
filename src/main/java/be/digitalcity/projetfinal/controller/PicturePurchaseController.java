package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PicturePurchaseDTO;
import be.digitalcity.projetfinal.models.form.PicturePurchaseForm;
import be.digitalcity.projetfinal.services.PicturePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("picturePurchase")
public class PicturePurchaseController {
    private final PicturePurchaseService service;

    @Autowired
    public PicturePurchaseController(PicturePurchaseService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<PicturePurchaseDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PicturePurchaseDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<PicturePurchaseDTO> insert(@Valid @RequestBody PicturePurchaseForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PicturePurchaseDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PicturePurchaseDTO> update(@PathVariable Long id, @Valid @RequestBody PicturePurchaseForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
