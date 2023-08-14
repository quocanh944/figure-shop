package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
