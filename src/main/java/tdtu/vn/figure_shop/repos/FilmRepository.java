package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Film;


public interface FilmRepository extends JpaRepository<Film, Long> {
}
