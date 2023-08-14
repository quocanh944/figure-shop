package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.vn.figure_shop.dto.FeedbackDTO;
import tdtu.vn.figure_shop.service.FeedbackService;


@RestController
@RequestMapping(value = "/api/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackResource {

    private final FeedbackService feedbackService;

    public FeedbackResource(final FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedback(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(feedbackService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createFeedback(@RequestBody @Valid final FeedbackDTO feedbackDTO) {
        final Long createdId = feedbackService.create(feedbackDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateFeedback(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final FeedbackDTO feedbackDTO) {
        feedbackService.update(id, feedbackDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFeedback(@PathVariable(name = "id") final Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
