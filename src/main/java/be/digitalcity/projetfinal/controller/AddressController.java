package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.AddressDTO;
import be.digitalcity.projetfinal.models.form.AddressForm;
import be.digitalcity.projetfinal.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "address")
public class AddressController {
    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<AddressDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> insert(@Valid @RequestBody AddressForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @Valid @RequestBody AddressForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
