package tdtu.vn.figure_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tdtu.vn.figure_shop.dto.OrderDTO;
import tdtu.vn.figure_shop.service.OrderService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/recent")
    @Operation(summary = "Admin side")
    public ResponseEntity<Page<OrderDTO>> ordersRecent(
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) Integer page,
            @RequestParam(
                    value = "size",
                    defaultValue = "5",
                    required = false
            ) Integer size
    ) {
        Page<OrderDTO> res = orderService.getOrdersRecent(page, size);
        return ResponseEntity.ok(res);
    }
}
