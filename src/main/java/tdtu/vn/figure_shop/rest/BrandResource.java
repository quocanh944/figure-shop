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
import tdtu.vn.figure_shop.dto.BrandDTO;
import tdtu.vn.figure_shop.service.BrandService;


@RestController
@RequestMapping(value = "/api/brands", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandResource {

    private final BrandService brandService;

    public BrandResource(final BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(brandService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBrand(@RequestBody @Valid final BrandDTO brandDTO) {
        final Long createdId = brandService.create(brandDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateBrand(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BrandDTO brandDTO) {
        brandService.update(id, brandDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBrand(@PathVariable(name = "id") final Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
