package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.UserEntity;

import java.util.Optional;


public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
