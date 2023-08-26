package tdtu.vn.figure_shop.dto;

import lombok.Data;

@Data
public class CreateFeedbackDTO {
    private String comment;
    private Long product;
    private int rate;
}
