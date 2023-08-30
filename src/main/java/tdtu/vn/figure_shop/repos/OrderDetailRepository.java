package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tdtu.vn.figure_shop.domain.OrderDetail;
import tdtu.vn.figure_shop.model.ProductCount;

import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT SUM(od.quantity) FROM OrderDetail od")
    Long getAllSoldProduct();

    List<OrderDetail> findAllByOrder_Id(Long orderId);

    @Query("SELECT new tdtu.vn.figure_shop.model.ProductCount(count(od.quantity), od.product.id) FROM OrderDetail od GROUP BY od.product")
    List<ProductCount> getBestSeller();
}
