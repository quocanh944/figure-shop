package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDTO {

    @Getter
    @Setter
    public static class SubProductDTO {
        @NotNull
        @Size(max = 255)
        private String name;

        @NotNull
        private Double price;

        @NotNull
        private Integer quantity;

        @NotNull
        private String description;

        private Long film;

        private Long brand;
    }

    private MultipartFile file;

    private SubProductDTO product;

}


