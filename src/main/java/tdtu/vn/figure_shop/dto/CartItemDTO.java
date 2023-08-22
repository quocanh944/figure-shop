package tdtu.vn.figure_shop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    public int quantity;

    public Double total;

    public ProductDTO productDTO;
}
