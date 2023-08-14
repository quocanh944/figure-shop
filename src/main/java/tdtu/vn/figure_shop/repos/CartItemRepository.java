package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.CartItem;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
