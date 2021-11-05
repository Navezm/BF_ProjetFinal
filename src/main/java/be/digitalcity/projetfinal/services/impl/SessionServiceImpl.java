package be.digitalcity.projetfinal.services.impl;

import be.digitalcity.projetfinal.config.jwt.JwtTokenProvider;
import be.digitalcity.projetfinal.exceptions.models.UsernamePasswordInvalidException;
import be.digitalcity.projetfinal.mappers.AddressMapper;
import be.digitalcity.projetfinal.mappers.UserMapper;
import be.digitalcity.projetfinal.models.dto.UserDTO;
import be.digitalcity.projetfinal.models.entity.User;
import be.digitalcity.projetfinal.models.form.userForm.UserLoginForm;
import be.digitalcity.projetfinal.models.form.userForm.UserRegisterForm;
import be.digitalcity.projetfinal.repository.GroupRepository;
import be.digitalcity.projetfinal.repository.UserRepository;
import be.digitalcity.projetfinal.services.SessionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AddressMapper addressMapper;
    private final GroupRepository groupRepository;

    public SessionServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, AddressMapper addressMapper, GroupRepository groupRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.addressMapper = addressMapper;
        this.groupRepository = groupRepository;
    }

    @Override
    public UserDTO login(UserLoginForm form) {
        try{
            User u = userRepository.findUserByUsername(form.getUsername())
                    .orElseThrow();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

            UserDTO dto = userMapper.toDto(u);
            dto.setToken(jwtTokenProvider.createToken(u));

            return dto;
        } catch (Exception ex){
            throw new UsernamePasswordInvalidException();
        }
    }

    @Override
    public UserDTO register(UserRegisterForm form) {
        User u = new User();
        u.setUsername(form.getUsername());
        u.setAddress(addressMapper.fromFormToEntity(form.getAddress()));
        u.setEmail(form.getEmail());
        u.setPassword(passwordEncoder.encode(form.getPassword()));

        u.setGroup(groupRepository.getById(2L));

        u.setAccountNonExpired(true);
        u.setCreditialsNonExpired(true);
        u.setAccountNonLocked(true);
        u.setEnabled(true);

        u = userRepository.save(u);

        UserDTO dto = userMapper.toDto(u);
        dto.setToken(jwtTokenProvider.createToken(u));

        return dto;
    }
}
