package be.digitalcity.projetfinal.controller;

import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserInsertForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
//    private final UserService userService;
//    private final UserMapper userMapper;
//
//    @Autowired
//    public UserController(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }
//
//    @PatchMapping("/user/{id}/roles")
//    public ResponseEntity<UserDTO> addRoles(@PathVariable() long id, @Valid @RequestBody()UserInsertForm form) {
//        User user = this.userService.findById(id);
//
//        this.userService.addRoles(user, form.getRoles());
//
//        return ResponseEntity.ok(userMapper.mapFromEntity(user));
//    }
}
