package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.CartItem;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.repos.CartItemRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;
    public List<CartItem> getCartItemByUserId(Long userId) {
        return cartItemRepository.findByUser_Id(userId);
    }

    public void addItemToCart(Long productID) {
        UserEntity user = userService.getUserById(userService.getUserIdByEmail(userService.getCurrentUser()));
        Product product = productService.getProductById(productID);
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
        }
        cartItemRepository.save(cartItem);
    }
}
