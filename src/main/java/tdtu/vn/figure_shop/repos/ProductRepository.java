package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
