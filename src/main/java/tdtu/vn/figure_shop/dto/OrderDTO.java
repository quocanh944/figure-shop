package tdtu.vn.figure_shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private Double total;
    private String address;
    private String name;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Bangkok")
    @JsonProperty("created_date")
    private OffsetDateTime createdDate;

    private List<OrderDetailDTO> orderDetailList;
}
