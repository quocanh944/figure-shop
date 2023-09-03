package tdtu.vn.figure_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.ListCartItemDTO;
import tdtu.vn.figure_shop.service.CartItemService;
import tdtu.vn.figure_shop.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/carts")
public class CartItemController {
    private final CartItemService cartItemService;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<ListCartItemDTO> viewCart() {
        return ResponseEntity.ok(
                cartItemService.getCartItemDTOByUserId(
                        userService.getUserIdByEmail(userService.getCurrentUser())
                )
        );
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItemToCart(@RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
        cartItemService.addItemToCart(productId, quantity);
        return ResponseEntity.ok("Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestParam Long productId, @RequestParam int quantity) {
        cartItemService.updateItem(productId, quantity);
        return ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeItemFromCart(@RequestParam Long productId) {
        cartItemService.removeItemToCart(productId);
        return ResponseEntity.ok("Successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearItems() {
        cartItemService.clearItems();
        return ResponseEntity.ok("Successfully");
    }
}
