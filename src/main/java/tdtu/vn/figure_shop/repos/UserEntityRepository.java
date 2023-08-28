package tdtu.vn.figure_shop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdtu.vn.figure_shop.domain.Role;
import tdtu.vn.figure_shop.domain.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
    Page<UserEntity> findByRoles(Role role, Pageable pageable);
}
