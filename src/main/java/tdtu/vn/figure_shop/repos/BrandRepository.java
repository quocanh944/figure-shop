package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long> {
}
