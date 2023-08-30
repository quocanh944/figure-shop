package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.domain.OrderDetail;
import tdtu.vn.figure_shop.dto.OrderDetailDTO;
import tdtu.vn.figure_shop.dto.ProductDTO;
import tdtu.vn.figure_shop.model.ProductCount;
import tdtu.vn.figure_shop.repos.OrderDetailRepository;
import tdtu.vn.figure_shop.repos.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;


    public List<OrderDetailDTO> getBestSeller(Integer amount) {
        List<ProductCount> allProducts = orderDetailRepository.getBestSeller();
        allProducts.sort((prod1, prod2) -> (int) (prod2.getCount() - prod1.getCount()));

        return allProducts.subList(0, amount).stream().map(productCount -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(Math.toIntExact(productCount.getCount()));
            orderDetail.setProduct(productService.getProductById(productCount.getProductId()));
            return mapToDTO(orderDetail, new OrderDetailDTO());
        }).toList();
    }


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
