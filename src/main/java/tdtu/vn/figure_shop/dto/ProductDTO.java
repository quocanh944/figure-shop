package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @Size(max = 255)
    private String image;

    @NotNull
    private String description;

    private Long film;

    private Long brand;

}
