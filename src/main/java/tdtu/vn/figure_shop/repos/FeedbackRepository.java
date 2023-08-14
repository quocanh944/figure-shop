package tdtu.vn.figure_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tdtu.vn.figure_shop.domain.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
