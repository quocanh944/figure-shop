package tdtu.vn.figure_shop.dto;

import lombok.Data;

@Data
public class FeedbackDetailDTO {
    private String comment;
    private String fullName;
    private String email;
    private String avatar;
    private int rate;
}
