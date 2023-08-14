package tdtu.vn.figure_shop.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeedbackDTO {

    private Long id;
    private String comment;
    private Long product;
    private Long user;

}
