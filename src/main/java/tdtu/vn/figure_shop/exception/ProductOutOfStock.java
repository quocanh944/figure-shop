package tdtu.vn.figure_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductOutOfStock extends RuntimeException {
    public ProductOutOfStock(String message) {
        super(message);
    }
}
