package tdtu.vn.figure_shop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDTO {

    private MultipartFile file;

    private ProductDTO product;

}
