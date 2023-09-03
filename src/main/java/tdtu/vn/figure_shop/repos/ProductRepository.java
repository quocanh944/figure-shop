package tdtu.vn.figure_shop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.domain.Film;
import tdtu.vn.figure_shop.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByFilmIn(List<Film> film, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByPriceBetween(double minPrice,double maxPrice,Pageable pageable);

    Page<Product> findByBrandIn(List<Brand> brands, Pageable pageable);

    Page<Product> findAllByBrandInAndFilmIn(List<Brand> brands, List<Film> film, Pageable pageable);
}
