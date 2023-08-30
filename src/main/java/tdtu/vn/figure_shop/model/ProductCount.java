package tdtu.vn.figure_shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCount {
    Long count;
    Long productId;

    public ProductCount(Long count, Long productId) {
        this.count = count;
        this.productId = productId;
    }
}
