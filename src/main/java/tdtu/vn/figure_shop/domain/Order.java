package tdtu.vn.figure_shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"order\"")
@Getter
@Setter
public class Order {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double total;

    @Column(columnDefinition = "longtext")
    private String address;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
