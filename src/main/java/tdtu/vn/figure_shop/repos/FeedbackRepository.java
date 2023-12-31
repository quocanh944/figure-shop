package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Feedback;
import tdtu.vn.figure_shop.domain.Product;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByProduct(Product product);
}
