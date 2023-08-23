package tdtu.vn.figure_shop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponse<T> {

    private Integer httpStatus;
    private String message;
    private T data;
}
