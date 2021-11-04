package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.entity.Picture;
import be.digitalcity.projetfinal.models.form.PictureForm;
import be.digitalcity.projetfinal.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "picture")
public class PictureController {
    private final PictureService service;

    @Autowired
    public PictureController(PictureService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<PictureDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PictureDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<List<PictureDTO>> getByType(@PathVariable Long id){
        return ResponseEntity.ok(service.findByType(id));
    }

    @GetMapping("/isAvailable")
    public ResponseEntity<List<PictureDTO>> getByAvailibility(){
        return ResponseEntity.ok(service.findByAvailability());
    }

    @PostMapping
    public ResponseEntity<PictureDTO> insert(@Valid @RequestBody PictureForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PictureDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PictureDTO> update(@PathVariable Long id, @Valid @RequestBody PictureForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
