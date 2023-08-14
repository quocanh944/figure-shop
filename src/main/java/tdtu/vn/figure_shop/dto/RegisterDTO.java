package tdtu.vn.figure_shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;
}
