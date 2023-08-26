package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdtu.vn.figure_shop.dto.CreateFeedbackDTO;
import tdtu.vn.figure_shop.dto.FeedbackDTO;
import tdtu.vn.figure_shop.dto.FeedbackDetailDTO;
import tdtu.vn.figure_shop.service.FeedbackService;


@RestController
@RequestMapping(value = "/api/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackResource {

    private final FeedbackService feedbackService;

    public FeedbackResource(final FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/product/{id}")
    @SecurityRequirements()
    public ResponseEntity<List<FeedbackDetailDTO>> getAllFeedbacks(@PathVariable(name = "id") final Long prodId) {
        return ResponseEntity.ok(feedbackService.findAllByProduct(prodId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createFeedback(@RequestBody @Valid final CreateFeedbackDTO createFeedbackDTO, Authentication authentication) {
        String emailUser = authentication.getName();
        final Long createdId = feedbackService.create(createFeedbackDTO, emailUser);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Admin side")
    public ResponseEntity<Long> updateFeedback(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final FeedbackDTO feedbackDTO) {
        feedbackService.update(id, feedbackDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @Operation(summary = "Admin side")
    public ResponseEntity<Void> deleteFeedback(@PathVariable(name = "id") final Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
