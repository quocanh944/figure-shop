package tdtu.vn.figure_shop.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private Double total;
    private long quantity;
    private ProductDTO product;
}
