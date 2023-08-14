package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.OrderDetail;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
