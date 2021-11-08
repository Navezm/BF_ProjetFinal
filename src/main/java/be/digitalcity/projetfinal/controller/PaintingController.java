package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.form.PaintingForm;
import be.digitalcity.projetfinal.services.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("painting")
public class PaintingController {
    private final PaintingService service;

    @Autowired
    public PaintingController(PaintingService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    @Secured(value= {"shopAccess"})
    public ResponseEntity<List<PaintingDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

//    @GetMapping("/type/{id}")
//    public ResponseEntity<List<PaintingDTO>> getByType(@PathVariable(value = "id") Long id){
//        return ResponseEntity.ok(service.findByType(id));
//    }

    @GetMapping("/isAvailable")
    @Secured({"shopAccess"})
    public ResponseEntity<List<PaintingDTO>> getByAvailibility(){
        return ResponseEntity.ok(service.findByAvailability());
    }

    @PostMapping
    public ResponseEntity<PaintingDTO> insert(@Valid @RequestBody PaintingForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaintingDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingDTO> update(@PathVariable Long id, @Valid @RequestBody PaintingForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
