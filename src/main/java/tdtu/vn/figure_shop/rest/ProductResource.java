package tdtu.vn.figure_shop.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdtu.vn.figure_shop.dto.CreateProductDTO;
import tdtu.vn.figure_shop.dto.OrderDetailDTO;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.dto.ProductDetailDTO;
import tdtu.vn.figure_shop.service.OrderDetailService;
import tdtu.vn.figure_shop.service.ProductService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private final ProductService productService;
    private final OrderDetailService orderDetailService;

    public ProductResource(final ProductService productService, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDetailDTO>> getAllProducts(
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) Integer page,
            @RequestParam(
                    value = "size",
                    defaultValue = "9",
                    required = false
            ) Integer size,
            @RequestParam(defaultValue = "ASC") String sort
    ) {

        return ResponseEntity.ok(productService.findAll(page, size, sort));
    }

    @GetMapping("/film/{id}")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDetailDTO>> getAllProductsByFilm(
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
            ) Integer size,
            @RequestParam(defaultValue = "ASC") String sort
    ) {

        return ResponseEntity.ok(productService.findAllByFilm(page, size, filmId,sort));
    }

    @GetMapping("/brand/{id}")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDetailDTO>> getProductsByBrand(
            @PathVariable(name = "id") Long brandId,
            @RequestParam(value = "page", defaultValue = "0",required = false) int page,
            @RequestParam(value = "size", defaultValue = "15",required = false) int size,
            @RequestParam(defaultValue = "ASC") String sort){
        return ResponseEntity.ok(productService.findProductBrand(brandId,page,size,sort));
    }

    @GetMapping("/byPriceRange")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDetailDTO>> getProductByPriceBetween(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam(value = "page",
                    defaultValue = "0",
                    required = false) int page,
            @RequestParam(value = "size",
                    defaultValue = "9",
                    required = false) int size,
            @RequestParam(defaultValue = "ASC") String direction){

        Page<ProductDetailDTO> products = productService.findProductPriceBetween(minPrice, maxPrice,page,size,direction);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDetailDTO>> getSearch(
            @RequestParam String name,
            @RequestParam(value = "page",
                    defaultValue = "0",
                    required = false
            )int page,
            @RequestParam(value = "size",
                    defaultValue = "9",
                    required = false)
            int size,
            @RequestParam(defaultValue = "ASC") String direction
    ){
        Page<ProductDetailDTO> productDTOS = productService.findProductsByName(name,page,size,direction);

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/{id}")
    @SecurityRequirements()
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ApiResponse(responseCode = "201")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product DTO",
            content = @Content(
                    schema = @Schema(implementation = CreateProductDTO.class),
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
            )
    )
    @Operation(summary = "Admin side")
    public ResponseEntity<String> createProduct(
            @RequestPart("file") MultipartFile file,
            @RequestPart("product") String product
            ) throws IOException {
        CreateProductDTO.SubProductDTO productDTO;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            productDTO = objectMapper.readValue(product, CreateProductDTO.SubProductDTO.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot convert json!");
        }

        productService.createProduct(
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getDescription(),
                productDTO.getFilm(),
                productDTO.getBrand(),
                file
        );
        return new ResponseEntity<>("Successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ApiResponse(responseCode = "201")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Product DTO",
            content = @Content(
                    schema = @Schema(implementation = CreateProductDTO.class),
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
            )
    )
    @Operation(summary = "Admin side")
    public ResponseEntity<String> update(
            @PathVariable(name = "id") Long productId,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("product") String product
          )throws IOException {
        ProductDTO productDTO = new ProductDTO();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            productDTO = objectMapper.readValue(product, ProductDTO.class);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot convert json!");
        }

        productService.updateProduct(
                productId,
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getDescription(),
                file,
                productDTO.getBrand(),
                productDTO.getFilm()
        );
        return ResponseEntity.ok("Product updated successfully");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Long> updateProduct(@PathVariable(name = "id") final Long id,
//            @RequestBody @Valid final ProductDTO productDTO) {
//        productService.update(id, productDTO);
//        return ResponseEntity.ok(id);
//    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    @Operation(summary = "Admin side")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") final Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/best-seller")
    @Operation(summary = "Admin side")
    public ResponseEntity<List<OrderDetailDTO>> getBestSeller(
            @RequestParam(
                    name = "amount",
                    defaultValue = "6"
            ) final Integer amount
    ) {
        return ResponseEntity.ok(orderDetailService.getBestSeller(amount));
    }

}
