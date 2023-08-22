package tdtu.vn.figure_shop.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.dto.CreateDTO;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.dto.ProductDetailDTO;
import tdtu.vn.figure_shop.service.ProductService;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
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

    @GetMapping("/byPriceRange")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDTO>> getProductByPriceBetween(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            @RequestParam(value = "page",
                    defaultValue = "0",
                    required = false) int page,
            @RequestParam(value = "size",
                    defaultValue = "9",
                    required = false) int size,
            @RequestParam(defaultValue = "ASC") String direction){
        Sort.Direction sort = direction.equalsIgnoreCase("ASC")? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort,"price"));
        Page<ProductDTO> products = productService.findProductPriceBetween(minPrice, maxPrice,pageable);
        return ResponseEntity.ok(products);
    }



    @GetMapping("/search")
    @SecurityRequirements()
    public ResponseEntity<Page<ProductDTO>> getSearch(
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
        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC")? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection,"price"));
        Page<ProductDTO> productDTOS = productService.findProductsByName(name,pageable);

        return ResponseEntity.ok(productDTOS);
    }



    @GetMapping("/brand")
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

    @PostMapping(value = "/create", consumes = {
            "multipart/form-data"
    })
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> createProduct(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("price") Double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("description") String description
//            @RequestParam(value = "brand", required = false) int brand,
//            @RequestParam(value = "film", required = false) int film
            ) throws IOException {
        productService.createProduct(name,price,quantity,description,file);
        return new ResponseEntity<>("successfully",HttpStatus.CREATED);
    }
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"
    })
    public ResponseEntity<String> update(
            @PathVariable(name = "id") Long productId,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam("price") Double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("description") String description

          ){
        try{
            CreateDTO createDTO = new CreateDTO();
            createDTO.setName(name);
            createDTO.setPrice(price);
            createDTO.setQuantity(quantity);
            createDTO.setDescription(description);
            productService.updateProduct(productId,createDTO,file);
            return ResponseEntity.ok("Product updated successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
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
