package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.*;
import tdtu.vn.figure_shop.dto.CreateOrderDTO;
import tdtu.vn.figure_shop.dto.OrderDTO;
import tdtu.vn.figure_shop.dto.OrderDetailDTO;
import tdtu.vn.figure_shop.exception.BadRequestException;
import tdtu.vn.figure_shop.repos.OrderDetailRepository;
import tdtu.vn.figure_shop.repos.OrderRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;
import tdtu.vn.figure_shop.repos.UserEntityRepository;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class OrderService {
    private final UserEntityRepository userEntityRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderDetailService orderDetailService;
    private final CartItemService cartItemService;
    private final EmailService emailService;


    public Page<OrderDTO> getOrdersRecent(Integer page, Integer size) {

        return orderRepository
                .findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "createdDate")))
                .map(order -> mapToDTO(order, new OrderDTO()));
    }

    public void checkout(CreateOrderDTO createOrderDTO, String emailUser) {
        UserEntity currentUser = userEntityRepository.findByEmail(emailUser).orElseThrow();
        List<CartItem> cartItems = cartItemService.getCartItemByUserId(currentUser.getId());
        if (cartItems.isEmpty()) {
            throw new BadRequestException("Cart Item is empty");
        }

        cartItemService.validateCartItems();
        Order order = new Order();
        order.setUser(currentUser);
        order.setName(createOrderDTO.getName());
        order.setPhoneNumber(createOrderDTO.getPhoneNumber());
        order.setAddress(createOrderDTO.getAddress());
        order.setCreatedDate(OffsetDateTime.now().plusHours(7L));

        Set<OrderDetail> orderDetails = new HashSet<>();
        AtomicReference<Double> total = new AtomicReference<>(0.0);

        cartItems.forEach(cartItem -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());

            Product product = productRepository.findById(cartItem.getProduct().getId()).orElseThrow();
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());

            productRepository.save(product);

            total.updateAndGet(v -> v + product.getPrice() * cartItem.getQuantity());
            orderDetails.add(orderDetail);
        });
        order.setOrderDetails(orderDetails);
        order.setTotal(total.get());
        orderRepository.saveAndFlush(order);
        orderDetailRepository.saveAllAndFlush(orderDetails);
        System.out.println(emailUser);
        cartItemService.clearItems();


        // gui mail ve don hang

        String orderDetail = "Order Name: " + order.getName() +
                "\nPhone: " + order.getPhoneNumber() +
                "\nAddress: "+ order.getAddress()+
                "\nTotal: " + order.getTotal()+
                "\nDate: " + order.getCreatedDate();

        emailService.sendOrderConfirmationEmail(emailUser,orderDetail);
    }

    public OrderDTO mapToDTO(Order order, OrderDTO orderDTO) {
        orderDTO.setAddress(order.getAddress());
        orderDTO.setName(order.getName());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setPhoneNumber(order.getPhoneNumber());
        orderDTO.setCreatedDate(order.getCreatedDate());
        List<OrderDetailDTO> orderDetailList = orderDetailRepository.findAllByOrder_Id(order.getId())
                .stream()
                .map(orderDetail -> orderDetailService.mapToDTO(orderDetail, new OrderDetailDTO()))
                .toList();
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
