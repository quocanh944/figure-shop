package tdtu.vn.figure_shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticDTO {
    private Long numberOfOrder;

    private Double totalRevenue;

    private Long numberOfSoldProduct;

    private Long numberOfUser;
}
