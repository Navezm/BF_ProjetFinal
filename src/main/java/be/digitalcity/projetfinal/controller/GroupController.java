package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.GroupDTO;
import be.digitalcity.projetfinal.models.form.GroupForm;
import be.digitalcity.projetfinal.services.GroupService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {
    private final GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<GroupDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<GroupDTO> insert(@Valid @RequestBody GroupForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDTO> update(@PathVariable Long id, @Valid @RequestBody GroupForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
