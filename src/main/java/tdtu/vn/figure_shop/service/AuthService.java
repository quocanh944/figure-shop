package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Role;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.LoginDTO;
import tdtu.vn.figure_shop.dto.RegisterDTO;
import tdtu.vn.figure_shop.exception.BadRequestException;
import tdtu.vn.figure_shop.repos.RoleRepository;
import tdtu.vn.figure_shop.repos.UserEntityRepository;
import tdtu.vn.figure_shop.security.JWTTokenProvider;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserEntityRepository userEntityRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    public void createUser(RegisterDTO registerDTO) {

        if(registerDTO == null){
            throw new BadRequestException("Please enter all the fields of user.");
        }
        Optional<UserEntity> findingUserByEmail = userEntityRepository.findByEmail(registerDTO.getEmail());
        if(findingUserByEmail.isPresent()){
            throw new BadRequestException("Your email was registered, please enter another email.");
        }
        if(registerDTO.getPassword().length() < 6){
            throw new BadRequestException("Your password must be at least 6 characters.");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new BadRequestException("Your password and confirm password not match.");
        }


        UserEntity newUser = new UserEntity();
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        newUser.setRoles(Collections.singletonList(role));

        userEntityRepository.save(newUser);
    }

    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(), loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.createToken(authentication);
    }
}
