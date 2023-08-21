package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tdtu.vn.figure_shop.domain.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.total) FROM Order o")
    public Double getTotalRevenue();
}
