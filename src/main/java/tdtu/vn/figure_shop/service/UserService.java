package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.UserDTO;
import tdtu.vn.figure_shop.repos.UserEntityRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;
    private PasswordEncoder passwordEncoder;
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }else{
            throw new RuntimeException("No User");
        }
    }
    public Long getUserIdByEmail(String email) {
        Optional<UserEntity> user = userEntityRepository.findByEmail(email);
        return user.orElseThrow().getId();
    }
    public UserDTO mapToDTO(UserEntity user, UserDTO userDTO) {
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }
    public UserDTO getUserInfo() {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        return mapToDTO(user, new UserDTO());
    }
    public boolean changeEmail(String confirmPw, String newEmail) {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        if (passwordEncoder.matches(confirmPw, user.getPassword())){
            user.setEmail(newEmail);
            user.setUpdatedDate(Instant.now());
            userEntityRepository.save(user);
            return true;
        } return false;
    }
    public boolean changePassword(String confirmPw, String newPw) {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        if (passwordEncoder.matches(confirmPw, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPw));
            user.setUpdatedDate(Instant.now());
            userEntityRepository.save(user);
            return true;
        } return false;
    }
    public void changeFullName(String newFullName) {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        user.setFullName(newFullName);
        user.setUpdatedDate(Instant.now());
        userEntityRepository.save(user);
    }
    public void changeAvatar(String newAvatar) {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        user.setAvatar(newAvatar);
        user.setUpdatedDate(Instant.now());
        userEntityRepository.save(user);
    }
    public void changePhoneNumber(String newPhoneNumber) {
        UserEntity user = userEntityRepository.findByEmail(getCurrentUser()).orElseThrow();
        user.setPhoneNumber(newPhoneNumber);
        user.setUpdatedDate(Instant.now());
        userEntityRepository.save(user);
    }
    public Optional<UserEntity> getUserById(Long id) {
        return userEntityRepository.findById(id);
    }
    public List<UserEntity> getAllUser() {
        return userEntityRepository.findAll();
    }
}
