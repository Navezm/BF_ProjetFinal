package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.DisponibilityDTO;
import be.digitalcity.projetfinal.models.form.DisponibilityForm;
import be.digitalcity.projetfinal.services.DisponibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "disponibility")
public class DisponibilityController {
    private final DisponibilityService service;

    @Autowired
    public DisponibilityController(DisponibilityService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<DisponibilityDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<DisponibilityDTO> insert(@Valid @RequestBody DisponibilityForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilityDTO> update(@PathVariable Long id, @Valid @RequestBody DisponibilityForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
