package tdtu.vn.figure_shop.dto;

import lombok.Getter;
import lombok.Setter;
import tdtu.vn.figure_shop.domain.Order;

@Getter
@Setter
public class UserDTO {
    private String email;

    private String fullName;

    private String avatar;

    private String phoneNumber;

    private OrderDTO orderDTO;
}
