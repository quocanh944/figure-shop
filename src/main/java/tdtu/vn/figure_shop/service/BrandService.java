package tdtu.vn.figure_shop.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Brand;
import tdtu.vn.figure_shop.dto.BrandDTO;
import tdtu.vn.figure_shop.repos.BrandRepository;
import tdtu.vn.figure_shop.util.NotFoundException;


@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<BrandDTO> findAll() {
        final List<Brand> brands = brandRepository.findAll(Sort.by("id"));
        return brands.stream()
                .map(brand -> mapToDTO(brand, new BrandDTO()))
                .toList();
    }

    public BrandDTO get(final Long id) {
        return brandRepository.findById(id)
                .map(brand -> mapToDTO(brand, new BrandDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BrandDTO brandDTO) {
        final Brand brand = new Brand();
        mapToEntity(brandDTO, brand);
        return brandRepository.save(brand).getId();
    }

    public void update(final Long id, final BrandDTO brandDTO) {
        final Brand brand = brandRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(brandDTO, brand);
        brandRepository.save(brand);
    }

    public void delete(final Long id) {
        brandRepository.deleteById(id);
    }

    public BrandDTO mapToDTO(final Brand brand, final BrandDTO brandDTO) {
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        return brandDTO;
    }

    private Brand mapToEntity(final BrandDTO brandDTO, final Brand brand) {
        brand.setName(brandDTO.getName());
        return brand;
    }

}
