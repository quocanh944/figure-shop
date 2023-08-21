package tdtu.vn.figure_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdtu.vn.figure_shop.domain.CartItem;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.service.CartItemService;
import tdtu.vn.figure_shop.service.ProductService;
import tdtu.vn.figure_shop.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
public class CartItemController {
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public List<CartItem> viewCart() {
        return cartItemService.getCartItemByUserId(userService.getUserIdByEmail(userService.getCurrentUser()));
    }

    @PostMapping("/add")
    public void addItemToCart(@RequestParam Long productId) {
        UserEntity user = userService.getUserById(userService.getUserIdByEmail(userService.getCurrentUser()));
        cartItemService.addItemToCart(productId);
    }
}
