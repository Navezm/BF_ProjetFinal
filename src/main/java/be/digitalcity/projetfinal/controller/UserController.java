package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.mappers.GroupMapper;
import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.*;
import be.digitalcity.projetfinal.models.entity.Group;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserAddGroup;
import be.digitalcity.projetfinal.models.form.userForm.UserAddRoleForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.models.form.userForm.UserUpdateForm;
import be.digitalcity.projetfinal.repository.UserRepository;
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
    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, PaintingPurchaseService paintingPurchaseService, PaintingQuotationService paintingQuotationService, PicturePurchaseService picturePurchaseService, ReservationService reservationService, GroupService groupService, GroupMapper groupMapper, UserRepository userRepository){
        this.service = userService;
        this.userMapper = userMapper;
        this.paintingPurchaseService = paintingPurchaseService;
        this.paintingQuotationService = paintingQuotationService;
        this.picturePurchaseService = picturePurchaseService;
        this.reservationService = reservationService;
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.userRepository = userRepository;
    }

    /**
     * Method used to get all the users from the DB
     * @return ResponseEntity<List<UserDTO>>
     */
    @GetMapping({""})
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Method used to get one user in particular
     * @param id
     * @return ResponseEntity<UserDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    /**
     * Method used to get the users by group
     * @param id
     * @return ResponseEntity<List<UserDTO>>
     */
    @GetMapping("/group/{id}")
    public ResponseEntity<List<UserDTO>> getByGroup(@PathVariable Long id){
        return ResponseEntity.ok(service.findByGroup(id));
    }

    /**
     * Method used to get the painting purchase by user
     * @param id
     * @return ResponseEntity<List<PaintingPurchaseDTO>>
     */
    @GetMapping("/{id}/paintingPurchase")
    public ResponseEntity<List<PaintingPurchaseDTO>> getPaintingPurchaseByUser(@PathVariable Long id){
        return ResponseEntity.ok(paintingPurchaseService.findByUser(id));
    }

    /**
     * Method used to get the painting quotation by user
     * @param id
     * @return ResponseEntity<List<PaintingQuotationDTO>>
     */
    @GetMapping("/{id}/paintingQuotation")
    public ResponseEntity<List<PaintingQuotationDTO>> getPaintingQuotationByUser(@PathVariable Long id){
        return ResponseEntity.ok(paintingQuotationService.findByUser(id));
    }

    /**
     * Method used to get the picture purchase by user
     * @param id
     * @return ResponseEntity<List<PicturePurchaseDTO>>
     */
    @GetMapping("/{id}/picturePurchase")
    public ResponseEntity<List<PicturePurchaseDTO>> getPicturePurchaseByUser(@PathVariable Long id){
        return ResponseEntity.ok(picturePurchaseService.findByUser(id));
    }

    /**
     * Method used to get the reservation by user
     * @param id
     * @return ResponseEntity<List<ReservationDTO>>
     */
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

    @PatchMapping("/{id}/roles")
    public ResponseEntity<UserDTO> addRoles(@PathVariable("id") User user, @Valid @RequestBody UserAddRoleForm form) {
        this.userRepository.save(this.service.addRoles(user, form));

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PatchMapping("/{id}/group")
    public ResponseEntity<UserDTO> addGroup(@PathVariable("id") User user, @Valid @RequestBody UserAddGroup form){
        Group group = groupMapper.toEntity(this.groupService.getOne(form.getId()));

        user.setGroup(group);

        this.userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
