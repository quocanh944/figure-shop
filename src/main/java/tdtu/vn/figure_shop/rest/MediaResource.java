package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
import tdtu.vn.figure_shop.dto.MediaDTO;
import tdtu.vn.figure_shop.service.MediaService;


@RestController
@RequestMapping(value = "/api/medias", produces = MediaType.APPLICATION_JSON_VALUE)
public class MediaResource {

    private final MediaService mediaService;

    public MediaResource(final MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    @SecurityRequirements()
    public ResponseEntity<List<MediaDTO>> getAllMedias() {
        return ResponseEntity.ok(mediaService.findAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirements()
    public ResponseEntity<MediaDTO> getMedia(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(mediaService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createMedia(@RequestBody @Valid final MediaDTO mediaDTO) {
        final Long createdId = mediaService.create(mediaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMedia(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MediaDTO mediaDTO) {
        mediaService.update(id, mediaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMedia(@PathVariable(name = "id") final Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
