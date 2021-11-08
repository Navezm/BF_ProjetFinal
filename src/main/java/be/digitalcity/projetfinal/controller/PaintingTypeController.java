package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.PaintingDTO;
import be.digitalcity.projetfinal.models.dto.PaintingTypeDTO;
import be.digitalcity.projetfinal.models.form.PaintingTypeForm;
import be.digitalcity.projetfinal.services.PaintingService;
import be.digitalcity.projetfinal.services.PaintingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "paintingType")
public class PaintingTypeController {
    private final PaintingTypeService service;
    private final PaintingService paintingService;

    @Autowired
    public PaintingTypeController(PaintingTypeService service, PaintingService paintingService) {
        this.service = service;
        this.paintingService = paintingService;
    }

    @GetMapping({""})
    public ResponseEntity<List<PaintingTypeDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingTypeDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<PaintingTypeDTO> insert(@Valid @RequestBody PaintingTypeForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaintingTypeDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaintingTypeDTO> update(@PathVariable Long id, @Valid @RequestBody PaintingTypeForm form){
        return ResponseEntity.ok(service.update(id, form));
    }

    @GetMapping("/{id:[0-9]+}/painting")
    public ResponseEntity<List<PaintingDTO>> getPaintingByType(@PathVariable Long id) {
        return ResponseEntity.ok(paintingService.findByType(id));
    }
}
