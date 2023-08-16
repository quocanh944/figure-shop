package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;


@Getter
@Setter
public class ProductDetailDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    private List<MediaDTO> medias;

    @NotNull
    private String description;

    private Long film;

    private Long brand;

}
