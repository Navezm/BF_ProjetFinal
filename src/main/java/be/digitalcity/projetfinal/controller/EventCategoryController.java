package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;
import be.digitalcity.projetfinal.services.EventCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "eventCategory")
public class EventCategoryController {
    private final EventCategoryService service;

    @Autowired
    public EventCategoryController(EventCategoryService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<EventCategoryDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventCategoryDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<EventCategoryDTO> insert(@Valid @RequestBody EventCategoryForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventCategoryDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventCategoryDTO> update(@PathVariable Long id, @Valid @RequestBody EventCategoryForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
