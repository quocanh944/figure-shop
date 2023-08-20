package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.dto.ProductDetailDTO;
import tdtu.vn.figure_shop.repos.BrandRepository;
import tdtu.vn.figure_shop.service.ProductService;
import tdtu.vn.figure_shop.util.NotFoundException;


@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private final ProductService productService;


    public ProductResource(final ProductService productService, BrandRepository brandRepository) {
        this.productService = productService;
    }

    @GetMapping
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) Integer page,
            @RequestParam(
                    value = "size",
                    defaultValue = "9",
                    required = false
            ) Integer size) {

        return ResponseEntity.ok(productService.findAll(page, size));
    }

    @GetMapping("/film/{id}")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDTO>> getAllProductsByFilm(
            @PathVariable(name = "id") Long filmId,
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) Integer page,
            @RequestParam(
                    value = "size",
                    defaultValue = "9",
                    required = false
            ) Integer size) {

        return ResponseEntity.ok(productService.findAllByFilm(page, size, filmId));
    }

    @GetMapping("/byPriceRangeDesc")
    public ResponseEntity<Page<ProductDTO>> getProductsByPriceRangeOrderByPriceDesc(
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "100000000") double maxPrice,
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) int page,
            @RequestParam(
                    value = "size",
                    defaultValue = "15",
                    required = false
            ) int size){
        Page<ProductDTO> productDTOS= productService.findProductsByPriceRangeOrderByPriceDesc(minPrice, maxPrice, page, size);
        return ResponseEntity.ok(productDTOS);


    }
    @GetMapping("/by-price-range")
    public ResponseEntity<Page<ProductDTO>> getProductByPriceBetween(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam(value = "page",
                    defaultValue = "0",
                    required = false) int page,
            @RequestParam(value = "size",
                    defaultValue = "15",
                    required = false) int size){
            Page<ProductDTO> products = productService.findProductPriceBetween(minPrice, maxPrice,page,size);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/byPriceRangeAsc")
    public ResponseEntity<Page<ProductDTO>> getProductsByPriceRangeOrderByPriceAsc(
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "100000000") double maxPrice,
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) int page,
            @RequestParam(
                    value = "size",
                    defaultValue = "15",
                    required = false
            ) int size) {
        Page<ProductDTO> productDTOS = productService.findProductsByPriceRangeOrderByPriceAsc(minPrice, maxPrice, page, size);
        return ResponseEntity.ok(productDTOS);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> getSearch(
            @RequestParam String name,
            @RequestParam(value = "page",
                    defaultValue = "0",
                    required = false
            )int page,
            @RequestParam(value = "size",
                    defaultValue = "15",
                    required = false)
            int size){
        Page<ProductDTO> productDTOS = productService.findProductsByName(name,page,size);

        return ResponseEntity.ok(productDTOS);
    }



    @GetMapping("/by-brand")
    public ResponseEntity<Page<ProductDTO>> getProductsByBrand(
            @RequestParam Long brandId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size){
        Page<ProductDTO> productDTOS = productService.findProductBrand(brandId,page,size);
        return ResponseEntity.ok(productDTOS);
    }


    @GetMapping("/{id}")
    @SecurityRequirements()
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProduct(@RequestBody @Valid final ProductDTO productDTO) {
        final Long createdId = productService.create(productDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateProduct(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ProductDTO productDTO) {
        productService.update(id, productDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") final Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
