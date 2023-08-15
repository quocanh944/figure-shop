package tdtu.vn.figure_shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.domain.Film;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.repos.BrandRepository;
import tdtu.vn.figure_shop.repos.FilmRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.util.NotFoundException;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final FilmRepository filmRepository;
    private final BrandRepository brandRepository;

    public ProductService(final ProductRepository productRepository,
            final FilmRepository filmRepository, final BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.filmRepository = filmRepository;
        this.brandRepository = brandRepository;
    }

    public Page<ProductDTO> findAll(Integer page, Integer size) {
        Page<Product> pageEntities = productRepository.findAll(PageRequest.of(page, size));
        return pageEntities.map((product -> mapToDTO(product, new ProductDTO())));
    }

    public Page<ProductDTO> findAllByFilm(Integer page, Integer size, Long id) {
        Film film = filmRepository.findById(id).orElseThrow();

        Page<Product> pageEntities = productRepository.findAllByFilm(film, PageRequest.of(page, size));

        return pageEntities.map(product -> mapToDTO(product, new ProductDTO()));
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setFilm(product.getFilm() == null ? null : product.getFilm().getId());
        productDTO.setBrand(product.getBrand() == null ? null : product.getBrand().getId());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());
        final Film film = productDTO.getFilm() == null ? null : filmRepository.findById(productDTO.getFilm())
                .orElseThrow(() -> new NotFoundException("film not found"));
        product.setFilm(film);
        final Brand brand = productDTO.getBrand() == null ? null : brandRepository.findById(productDTO.getBrand())
                .orElseThrow(() -> new NotFoundException("brand not found"));
        product.setBrand(brand);
        return product;
    }

}
