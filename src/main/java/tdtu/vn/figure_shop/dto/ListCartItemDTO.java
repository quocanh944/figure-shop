package tdtu.vn.figure_shop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListCartItemDTO {
    private Double total;

    private List<CartItemDTO> cartItems;
}
