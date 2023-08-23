package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderDTO {
    @NotNull
    private String address;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;
}
