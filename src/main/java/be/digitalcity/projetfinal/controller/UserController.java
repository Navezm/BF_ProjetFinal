package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.*;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserAddRoleForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.models.form.userForm.UserUpdateForm;
import be.digitalcity.projetfinal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;
    private final PaintingPurchaseService paintingPurchaseService;
    private final PaintingQuotationService paintingQuotationService;
    private final PicturePurchaseService picturePurchaseService;
    private final ReservationService reservationService;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, PaintingPurchaseService paintingPurchaseService, PaintingQuotationService paintingQuotationService, PicturePurchaseService picturePurchaseService, ReservationService reservationService){
        this.service = userService;
        this.userMapper = userMapper;
        this.paintingPurchaseService = paintingPurchaseService;
        this.paintingQuotationService = paintingQuotationService;
        this.picturePurchaseService = picturePurchaseService;
        this.reservationService = reservationService;
    }

    @GetMapping({"", "/all"})
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<UserDTO>> getByGroup(@PathVariable Long id){
        return ResponseEntity.ok(service.findByGroup(id));
    }

    @GetMapping("/{id}/paintingPurchase")
    public ResponseEntity<List<PaintingPurchaseDTO>> getPaintingPurchaseByUser(@PathVariable Long id){
        return ResponseEntity.ok(paintingPurchaseService.findByUser(id));
    }

    @GetMapping("/{id}/paintingQuotation")
    public ResponseEntity<List<PaintingQuotationDTO>> getPaintingQuotationByUser(@PathVariable Long id){
        return ResponseEntity.ok(paintingQuotationService.findByUser(id));
    }

    @GetMapping("/{id}/picturePurchase")
    public ResponseEntity<List<PicturePurchaseDTO>> getPicturePurchaseByUser(@PathVariable Long id){
        return ResponseEntity.ok(picturePurchaseService.findByUser(id));
    }

    @GetMapping("/{id}/reservation")
    public ResponseEntity<List<ReservationDTO>> getReservationByUser(@PathVariable Long id){
        return ResponseEntity.ok(reservationService.findByUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserRegisterForm form){
        return ResponseEntity.ok(service.insert(form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateForm form){
        return ResponseEntity.ok(service.update(id, form));
    }

    @PatchMapping("/user/{id}/roles")
    public ResponseEntity<UserDTO> addRoles(@PathVariable() long id, @Valid @RequestBody UserAddRoleForm form) {
        User user = userMapper.toEntity(this.service.getOne(id));

        this.service.addRoles(user, form);

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
