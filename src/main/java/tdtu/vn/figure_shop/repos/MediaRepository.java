package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Media;
import tdtu.vn.figure_shop.domain.Product;

import java.util.List;


public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findAllByProduct(Product product);
}
