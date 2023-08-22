package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tdtu.vn.figure_shop.domain.CartItem;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.domain.UserEntity;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser_Id(Long userId);
    CartItem findByUserAndProduct(UserEntity user, Product product);
}
