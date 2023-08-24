package tdtu.vn.figure_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdtu.vn.figure_shop.repos.OrderDetailRepository;
import tdtu.vn.figure_shop.repos.OrderRepository;
import tdtu.vn.figure_shop.repos.RoleRepository;
import tdtu.vn.figure_shop.repos.UserEntityRepository;

@Service
@AllArgsConstructor
public class StatisticService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;


    public Long getNumberOfOrder() {

        return orderDetailRepository.count();
    }

    public Double getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }

    public Long getAllSoldProduct() {
        return orderDetailRepository.getAllSoldProduct();
    }

    public Long getNumberOfUser() {

        return userEntityRepository
                .findAll()
                .stream()
                .filter(userEntity -> userEntity.getRoles().contains(roleRepository.findByName("USER").orElseThrow()))
                .count();
    }
}
