package tdtu.vn.figure_shop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.domain.Film;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.dto.*;
import tdtu.vn.figure_shop.exception.BadRequestException;
import tdtu.vn.figure_shop.model.MediaEnum;
import tdtu.vn.figure_shop.repos.BrandRepository;
import tdtu.vn.figure_shop.repos.FilmRepository;
import tdtu.vn.figure_shop.repos.MediaRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.util.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final FilmRepository filmRepository;
    private final BrandRepository brandRepository;
    private final MediaRepository mediaRepository;
    private final FireBaseService fireBaseService;
    private final FilmService filmService;
    private final BrandService brandService;

    public Page<ProductDetailDTO> findAll(Integer page, Integer size, String sortDirection) {
        Sort.Direction direction= sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"price");
        Page<Product> pageEntities = productRepository.findAll(PageRequest.of(page, size, sort));
        return pageEntities.map((product -> mapToDetailDTO(product, new ProductDetailDTO())));
    }

    public Page<ProductDetailDTO> findAllByFilm(Integer page, Integer size, Long id,String sortDirection) {
        Film film = filmRepository.findById(id).orElseThrow();
        Sort.Direction direction= sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"price");
        Page<Product> pageEntities = productRepository.findAllByFilm(film, PageRequest.of(page, size,sort));

        return pageEntities.map(product -> mapToDetailDTO(product, new ProductDetailDTO()));
    }

    public Page<ProductDetailDTO> findProductsByName(String name,Integer page, Integer size,String sortDirection){
        Sort.Direction direction= sortDirection.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"price");
        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name,PageRequest.of(page,size,sort));
        return products.map(product -> mapToDetailDTO(product,new ProductDetailDTO()));
    }

    public Page<ProductDetailDTO> findProductBrand(Long brandId,int page,int size,String sortDirection){
        Brand brand = brandRepository.findById(brandId).orElseThrow();
        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC") ?Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"price");
//
        Page<Product> products = productRepository.findByBrand(brand,PageRequest.of(page,size,sort));
        return products.map(product -> mapToDetailDTO(product,new ProductDetailDTO()));
    }

    public Page<ProductDetailDTO> findProductPriceBetween(double minPrice, double maxPrice,int page,int size,String sortDirection){
        Sort.Direction direction = sortDirection.equalsIgnoreCase("ASC") ?Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"price");
        Page<Product> products =productRepository.findByPriceBetween(minPrice,maxPrice,PageRequest.of(page,size,sort));
        return products.map(product -> mapToDetailDTO(product,new ProductDetailDTO()));
    }


    public ProductDetailDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDetailDTO(product, new ProductDetailDTO()))
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

    public void updateProduct(
            Long productId,
            String name,
            Double price,
            int quantity,
            String description,
            MultipartFile image,
            Long brandId,
            Long filmId
    ) throws IOException {
        Product productToUpdate = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new EntityNotFoundException("Film not found"));
        productToUpdate.setName(name);
        productToUpdate.setPrice(price);
        productToUpdate.setQuantity(quantity);
        productToUpdate.setDescription(description);
        productToUpdate.setBrand(brand);
        productToUpdate.setFilm(film);

        if(image != null && image.getSize() > 0){
            String imageURL = String.valueOf(fireBaseService.uploadFile(image));
            System.out.println("Delete current image: " + fireBaseService.deleteFileInFirebase(productToUpdate.getImage()));
            productToUpdate.setImage(imageURL);
        }
        productRepository.save(productToUpdate);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
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

    private ProductDetailDTO mapToDetailDTO(final Product product, final ProductDetailDTO productDetailDTO) {
        productDetailDTO.setId(product.getId());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setPrice(product.getPrice());
        productDetailDTO.setQuantity(product.getQuantity());
        productDetailDTO.setDescription(product.getDescription());

        List<MediaDTO> medias = new ArrayList<>();

        MediaDTO thumbnail = new MediaDTO();
        thumbnail.setProduct(product.getId());
        thumbnail.setType(MediaEnum.IMAGE);
        thumbnail.setUrl(product.getImage());
        medias.add(thumbnail);
        medias.addAll(mediaRepository.findAllByProduct(product).stream().map(media -> {
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setProduct(media.getProduct().getId());
            mediaDTO.setType(media.getType());
            mediaDTO.setUrl(media.getUrl());
            return mediaDTO;
        }).toList());

        productDetailDTO.setMedias(medias);
        productDetailDTO.setFilm(
                product.getFilm() == null
                        ? null
                        : filmService.mapToDTO(
                                filmRepository.findById(product.getFilm().getId()).orElseThrow(),
                                new FilmDTO()
                )
        );
        productDetailDTO.setBrand(
                product.getBrand() == null
                        ? null
                        : brandService.mapToDTO(
                        brandRepository.findById(product.getFilm().getId()).orElseThrow(),
                        new BrandDTO()
                )
        );
        return productDetailDTO;
    }
    public void createProduct(
            String name,
            Double price,
            int quantity,
            String description,
            Long filmId,
            Long brandId,
            MultipartFile image
    ) throws IOException {
        String imageURL = String.valueOf(fireBaseService.uploadFile(image));
        Product product = new Product();
        product.setName(name);
        product.setImage(imageURL);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDescription(description);
        product.setBrand(
                brandId != null
                        ?
                        brandRepository.findById(brandId)
                                .orElseThrow(
                                        () -> new BadRequestException("Cannot find brand with brand id: " + brandId)
                                )
                        : null
        );
        product.setFilm(
                filmId != null
                        ?
                        filmRepository.findById(filmId)
                                .orElseThrow(() -> new BadRequestException("Cannot find brand with film id: " + filmId))
                        : null
        );
        System.out.println(imageURL);
        productRepository.save(product);

    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
