package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.CartItem;
import tdtu.vn.figure_shop.domain.Product;
import tdtu.vn.figure_shop.domain.UserEntity;
import tdtu.vn.figure_shop.dto.CartItemDTO;
import tdtu.vn.figure_shop.dto.ListCartItemDTO;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.repos.CartItemRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.repos.UserEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserEntityRepository userEntityRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductService productService;

    public ListCartItemDTO getCartItemDTOByUserId(Long userId) {
        ListCartItemDTO listCartItemDTO = new ListCartItemDTO();
        listCartItemDTO.setCartItems(
                cartItemRepository.findByUser_Id(userId)
                        .stream()
                        .map(cartItem -> mapToDTO(cartItem, new CartItemDTO()))
                        .collect(Collectors.toList()));
        listCartItemDTO.setTotal(listCartItemDTO.getCartItems().stream().map(CartItemDTO::getTotal).reduce(0.0, Double::sum));

        return listCartItemDTO;
    }

    public void addItemToCart(Long productID) {
        UserEntity user = userEntityRepository.findByEmail(userService.getCurrentUser()).orElseThrow();
        Product product = productRepository.findById(productID).orElseThrow();
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

    public CartItemDTO mapToDTO(CartItem cartItem, CartItemDTO cartItemDTO) {
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setProductDTO(
                productService.mapToDTO(
                        productService.getProductById(cartItem.getProduct().getId()),
                        new ProductDTO()
                )
        );
        cartItemDTO.setTotal(cartItemDTO.getQuantity() * cartItemDTO.getProductDTO().getPrice());
        return cartItemDTO;
    }

    public void updateItem(Long productID, int quantity) {
        UserEntity user = userEntityRepository.findByEmail(userService.getCurrentUser()).orElseThrow();
        Product product = productRepository.findById(productID).orElseThrow();
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    public void removeItemToCart(Long productID) {
        UserEntity user = userEntityRepository.findByEmail(userService.getCurrentUser()).orElseThrow();
        Product product = productRepository.findById(productID).orElseThrow();
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        cartItemRepository.delete(cartItem);
    }

    public void clearItems() {
        UserEntity user = userEntityRepository.findByEmail(userService.getCurrentUser()).orElseThrow();
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(user.getId());
        cartItemRepository.deleteAll(cartItems);
    }
}
