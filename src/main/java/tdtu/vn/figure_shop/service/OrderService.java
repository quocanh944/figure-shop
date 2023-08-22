package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.Order;
import tdtu.vn.figure_shop.dto.OrderDTO;
import tdtu.vn.figure_shop.dto.OrderDetailDTO;
import tdtu.vn.figure_shop.repos.OrderDetailRepository;
import tdtu.vn.figure_shop.repos.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailService orderDetailService;

    public Page<OrderDTO> getOrdersRecent(Integer page, Integer size) {

        return orderRepository
                .findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "createdDate")))
                .map(order -> mapToDTO(order, new OrderDTO()));
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
