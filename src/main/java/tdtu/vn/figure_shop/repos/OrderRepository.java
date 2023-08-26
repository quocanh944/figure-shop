package tdtu.vn.figure_shop.repos;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import tdtu.vn.figure_shop.domain.Order;
import tdtu.vn.figure_shop.domain.UserEntity;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.total) FROM Order o")
    public Double getTotalRevenue();
    public List<Order> findAllByUser(UserEntity user);
}
