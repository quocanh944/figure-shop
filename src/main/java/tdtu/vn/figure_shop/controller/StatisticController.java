package tdtu.vn.figure_shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdtu.vn.figure_shop.dto.StatisticDTO;
import tdtu.vn.figure_shop.service.StatisticService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping()
    @Operation(summary = "Admin side")
    public ResponseEntity<StatisticDTO> getStatistic() {

        return ResponseEntity.ok(
                StatisticDTO.builder()
                        .numberOfOrder(statisticService.getNumberOfOrder())
                        .totalRevenue(statisticService.getTotalRevenue())
                        .numberOfSoldProduct(statisticService.getAllSoldProduct())
                        .numberOfUser(statisticService.getNumberOfUser())
                        .build()
        );
    }
}
