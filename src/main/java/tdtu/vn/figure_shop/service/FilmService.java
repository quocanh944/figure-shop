package tdtu.vn.figure_shop.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Film;
import tdtu.vn.figure_shop.dto.FilmDTO;
import tdtu.vn.figure_shop.repos.FilmRepository;
import tdtu.vn.figure_shop.util.NotFoundException;


@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public List<FilmDTO> findAll() {
        final List<Film> films = filmRepository.findAll(Sort.by("id"));
        return films.stream()
                .map(film -> mapToDTO(film, new FilmDTO()))
                .toList();
    }

    public FilmDTO get(final Long id) {
        return filmRepository.findById(id)
                .map(film -> mapToDTO(film, new FilmDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final FilmDTO filmDTO) {
        final Film film = new Film();
        mapToEntity(filmDTO, film);
        return filmRepository.save(film).getId();
    }

    public void update(final Long id, final FilmDTO filmDTO) {
        final Film film = filmRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(filmDTO, film);
        filmRepository.save(film);
    }

    public void delete(final Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO mapToDTO(final Film film, final FilmDTO filmDTO) {
        filmDTO.setId(film.getId());
        filmDTO.setName(film.getName());
        return filmDTO;
    }

    private Film mapToEntity(final FilmDTO filmDTO, final Film film) {
        film.setName(filmDTO.getName());
        return film;
    }

}
