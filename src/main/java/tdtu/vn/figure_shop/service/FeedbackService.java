package tdtu.vn.figure_shop.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Feedback;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.FeedbackDTO;
import tdtu.vn.figure_shop.repos.FeedbackRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.repos.UserEntityRepository;
import tdtu.vn.figure_shop.util.NotFoundException;


@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProductRepository productRepository;
    private final UserEntityRepository userEntityRepository;

    public FeedbackService(final FeedbackRepository feedbackRepository,
            final ProductRepository productRepository,
            final UserEntityRepository userEntityRepository) {
        this.feedbackRepository = feedbackRepository;
        this.productRepository = productRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public List<FeedbackDTO> findAll() {
        final List<Feedback> feedbacks = feedbackRepository.findAll(Sort.by("id"));
        return feedbacks.stream()
                .map(feedback -> mapToDTO(feedback, new FeedbackDTO()))
                .toList();
    }

    public FeedbackDTO get(final Long id) {
        return feedbackRepository.findById(id)
                .map(feedback -> mapToDTO(feedback, new FeedbackDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final FeedbackDTO feedbackDTO) {
        final Feedback feedback = new Feedback();
        mapToEntity(feedbackDTO, feedback);
        return feedbackRepository.save(feedback).getId();
    }

    public void update(final Long id, final FeedbackDTO feedbackDTO) {
        final Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(feedbackDTO, feedback);
        feedbackRepository.save(feedback);
    }

    public void delete(final Long id) {
        feedbackRepository.deleteById(id);
    }

    private FeedbackDTO mapToDTO(final Feedback feedback, final FeedbackDTO feedbackDTO) {
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setComment(feedback.getComment());
        feedbackDTO.setProduct(feedback.getProduct() == null ? null : feedback.getProduct().getId());
        feedbackDTO.setUser(feedback.getUser() == null ? null : feedback.getUser().getId());
        return feedbackDTO;
    }

    private Feedback mapToEntity(final FeedbackDTO feedbackDTO, final Feedback feedback) {
        feedback.setComment(feedbackDTO.getComment());
        final Product product = feedbackDTO.getProduct() == null ? null : productRepository.findById(feedbackDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        feedback.setProduct(product);
        final UserEntity user = feedbackDTO.getUser() == null ? null : userEntityRepository.findById(feedbackDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        feedback.setUser(user);
        return feedback;
    }

}
