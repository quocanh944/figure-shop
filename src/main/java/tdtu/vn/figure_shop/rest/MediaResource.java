package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdtu.vn.figure_shop.dto.MediaDTO;
import tdtu.vn.figure_shop.model.MediaEnum;
import tdtu.vn.figure_shop.service.FireBaseService;
import tdtu.vn.figure_shop.service.MediaService;


@RestController
@RequestMapping(value = "/api/medias", produces = MediaType.APPLICATION_JSON_VALUE)
public class MediaResource {

    private final MediaService mediaService;
    private final FireBaseService fireBaseService;

    public MediaResource(final MediaService mediaService, FireBaseService fireBaseService) {
        this.mediaService = mediaService;
        this.fireBaseService = fireBaseService;
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

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    @Operation(summary = "Admin side")
    public ResponseEntity<Long> createMedia(@RequestParam MediaEnum type,
                                            @RequestParam Long productID,
                                            @RequestParam("file") MultipartFile file
    ) throws Exception
    {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setType(type);
        mediaDTO.setProduct(productID);
        mediaDTO.setUrl(fireBaseService.uploadFile(file));
        final Long createdId = mediaService.create(mediaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Admin side")
    public ResponseEntity<Long> updateMedia(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MediaDTO mediaDTO) {
        mediaService.update(id, mediaDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @Operation(summary = "Admin side")
    public ResponseEntity<Void> deleteMedia(@PathVariable(name = "id") final Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
