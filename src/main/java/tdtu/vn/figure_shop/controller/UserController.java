package tdtu.vn.figure_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.UserDTO;
import tdtu.vn.figure_shop.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/")
    public ResponseEntity<UserDTO> getInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }

    @PutMapping("/update/email")
    public ResponseEntity<String> changeEmail(@RequestParam String currentPw, @RequestParam String newEmail) {
        if (userService.changeEmail(currentPw, newEmail)) {return ResponseEntity.ok("Successfully"); }
        else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password not match.");}
    }

    @PutMapping("/update/password")
    public ResponseEntity<String> changePassword(@RequestParam String currentPw, @RequestParam String newPw) {
        if (userService.changePassword(currentPw, newPw)) {return ResponseEntity.ok("Successfully"); }
        else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current password not match.");}
    }

    @PutMapping("/update/name")
    public ResponseEntity<String> changeFullName(@RequestParam String newFullName) {
        userService.changeFullName(newFullName);
        return ResponseEntity.ok("Successfully");
    }

    @PutMapping("/update/avatar")
    public ResponseEntity<String> changeAvatar(@RequestParam String newAvatar) {
        userService.changeAvatar(newAvatar);
        return ResponseEntity.ok("Successfully");
    }

    @PutMapping("/update/phone-number")
    public ResponseEntity<String> changePhoneNumber(@RequestParam String newPhoneNumber) {
        userService.changePhoneNumber(newPhoneNumber);
        return ResponseEntity.ok("Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserEntity>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/all-user")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}
