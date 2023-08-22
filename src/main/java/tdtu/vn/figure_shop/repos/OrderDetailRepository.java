package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tdtu.vn.figure_shop.domain.OrderDetail;

import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT SUM(od.quantity) FROM OrderDetail od")
    public Long getAllSoldProduct();

    public List<OrderDetail> findAllByOrder_Id(Long orderId);
}
