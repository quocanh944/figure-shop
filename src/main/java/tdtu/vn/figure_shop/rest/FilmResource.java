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
import tdtu.vn.figure_shop.dto.FilmDTO;
import tdtu.vn.figure_shop.service.FilmService;


@RestController
@RequestMapping(value = "/api/films", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilmResource {

    private final FilmService filmService;

    public FilmResource(final FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        return ResponseEntity.ok(filmService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilm(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(filmService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createFilm(@RequestBody @Valid final FilmDTO filmDTO) {
        final Long createdId = filmService.create(filmDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateFilm(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final FilmDTO filmDTO) {
        filmService.update(id, filmDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteFilm(@PathVariable(name = "id") final Long id) {
        filmService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
