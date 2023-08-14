package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FilmDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

}
