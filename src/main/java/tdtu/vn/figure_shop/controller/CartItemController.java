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
        cartItemService.addItemToCart(productId);
    }
    @PostMapping("/update")
    public void updateItem(@RequestParam Long productId, @RequestParam int quantity) {
        cartItemService.updateItem(productId, quantity);
    }
    @GetMapping("/remove")
    public void removeItemFromCart(@RequestParam Long productId) {
        cartItemService.removeItemToCart(productId);
    }
    @GetMapping("/clear")
    public void clearItems() {
        cartItemService.clearItems();
    }
}
