package tdtu.vn.figure_shop.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Media;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.dto.MediaDTO;
import tdtu.vn.figure_shop.repos.MediaRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.util.NotFoundException;


@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final ProductRepository productRepository;

    public MediaService(final MediaRepository mediaRepository,
            final ProductRepository productRepository) {
        this.mediaRepository = mediaRepository;
        this.productRepository = productRepository;
    }

    public List<MediaDTO> findAll() {
        final List<Media> medias = mediaRepository.findAll(Sort.by("id"));
        return medias.stream()
                .map(media -> mapToDTO(media, new MediaDTO()))
                .toList();
    }

    public MediaDTO get(final Long id) {
        return mediaRepository.findById(id)
                .map(media -> mapToDTO(media, new MediaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MediaDTO mediaDTO) {
        final Media media = new Media();
        mapToEntity(mediaDTO, media);
        return mediaRepository.save(media).getId();
    }

    public void update(final Long id, final MediaDTO mediaDTO) {
        final Media media = mediaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(mediaDTO, media);
        mediaRepository.save(media);
    }

    public void delete(final Long id) {
        mediaRepository.deleteById(id);
    }

    private MediaDTO mapToDTO(final Media media, final MediaDTO mediaDTO) {
        mediaDTO.setId(media.getId());
        mediaDTO.setType(media.getType());
        mediaDTO.setUrl(media.getUrl());
        mediaDTO.setProduct(media.getProduct() == null ? null : media.getProduct().getId());
        return mediaDTO;
    }

    private Media mapToEntity(final MediaDTO mediaDTO, final Media media) {
        media.setType(mediaDTO.getType());
        media.setUrl(mediaDTO.getUrl());
        final Product product = mediaDTO.getProduct() == null ? null : productRepository.findById(mediaDTO.getProduct())
                .orElseThrow(() -> new NotFoundException("product not found"));
        media.setProduct(product);
        return media;
    }

}
