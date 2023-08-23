package tdtu.vn.figure_shop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.vn.figure_shop.dto.AuthResponseDTO;
import tdtu.vn.figure_shop.dto.LoginDTO;
import tdtu.vn.figure_shop.dto.RegisterDTO;
import tdtu.vn.figure_shop.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/register")
    @SecurityRequirements()
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        authService.createUser(registerDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PostMapping("/login")
    @SecurityRequirements()
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        String token = authService.login(loginDTO);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }
}
