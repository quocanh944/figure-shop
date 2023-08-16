package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tdtu.vn.figure_shop.model.MediaEnum;


@Getter
@Setter
public class MediaDTO {

    @NotNull
    private MediaEnum type;

    @NotNull
    @Size(max = 255)
    private String url;

    private Long product;

}
