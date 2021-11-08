package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.models.dto.EventCategoryDTO;
import be.digitalcity.projetfinal.models.dto.PictureDTO;
import be.digitalcity.projetfinal.models.dto.ReservationDTO;
import be.digitalcity.projetfinal.models.form.EventCategoryForm;
import be.digitalcity.projetfinal.services.EventCategoryService;
import be.digitalcity.projetfinal.services.PictureService;
import be.digitalcity.projetfinal.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "eventCategory")
public class EventCategoryController {
    private final EventCategoryService service;
    private final PictureService pictureService;
    private final ReservationService reservationService;

    @Autowired
    public EventCategoryController(EventCategoryService service, PictureService pictureService, ReservationService reservationService) {
        this.service = service;
        this.pictureService = pictureService;
        this.reservationService = reservationService;
    }

    @GetMapping({""})
    public ResponseEntity<List<EventCategoryDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventCategoryDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/{id}/pictures")
    public ResponseEntity<List<PictureDTO>> getPictureByType(@PathVariable Long id){
        return ResponseEntity.ok(pictureService.findByType(id));
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationDTO>> getReservationByType(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.findByEventType(id));
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
