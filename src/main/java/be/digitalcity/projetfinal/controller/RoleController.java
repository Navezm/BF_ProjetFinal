package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.RoleDTO;
import be.digitalcity.projetfinal.models.form.RoleForm;
import be.digitalcity.projetfinal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "role")
public class RoleController {
    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<RoleDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@Valid @RequestBody RoleForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoleDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @Valid @RequestBody RoleForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
