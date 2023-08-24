package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.repos.UserEntityRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserEntityRepository userRepository;
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }else{
            throw new RuntimeException("No User");
        }
    }
    public Long getUserIdByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        return user.orElseThrow().getId();
    }
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
