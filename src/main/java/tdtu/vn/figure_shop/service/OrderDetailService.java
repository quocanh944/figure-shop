package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.OrderDetail;
import tdtu.vn.figure_shop.dto.OrderDetailDTO;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.repos.ProductRepository;

@Service
@AllArgsConstructor
public class OrderDetailService {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public OrderDetailDTO mapToDTO(OrderDetail orderDetail, OrderDetailDTO orderDetailDTO) {
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setProduct(
                productService.mapToDTO(
                        productRepository.findById(orderDetail.getProduct().getId()).orElseThrow(),
                        new ProductDTO()
                )
        );
        orderDetailDTO.setTotal(orderDetailDTO.getQuantity() * orderDetailDTO.getProduct().getPrice());
        return orderDetailDTO;
    }

}
