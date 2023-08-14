package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BrandDTO {

    private Long id;

    @Size(max = 255)
    private String name;

}
