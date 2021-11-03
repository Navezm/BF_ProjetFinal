package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.form.ReservationForm;
import be.digitalcity.projetfinal.services.ReservationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "reservation")
public class ReservationController {
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<ReservationDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationDTO>> getByUser(@PathVariable Long id){
        return ResponseEntity.ok(service.findByUser(id));
    }

    @GetMapping("/date")
    public ResponseEntity<List<ReservationDTO>> getByDate(@RequestBody LocalDate date){
        return ResponseEntity.ok(service.findByDate(date));
    }

    @GetMapping("/eventType/{id}")
    public ResponseEntity<List<ReservationDTO>> getByEventType(@PathVariable Long id){
        return ResponseEntity.ok(service.findByEventType(id));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> insert(@Valid @RequestBody ReservationForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @Valid @RequestBody ReservationForm form){
        return ResponseEntity.ok(service.update(id, form));
    }
}
