package tdtu.vn.figure_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.ListCartItemDTO;
import tdtu.vn.figure_shop.service.CartItemService;
import tdtu.vn.figure_shop.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartItemController {
    private final CartItemService cartItemService;
    private final UserService userService;

    @GetMapping("/")
    public ListCartItemDTO viewCart() {
        return cartItemService.getCartItemByUserId(userService.getUserIdByEmail(userService.getCurrentUser()));
    }

    @PostMapping("/add")
    public void addItemToCart(@RequestParam Long productId) {
        UserEntity user = userService.getUserById(userService.getUserIdByEmail(userService.getCurrentUser()));
        cartItemService.addItemToCart(productId);
    }
}
