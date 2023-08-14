package tdtu.vn.figure_shop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String fullName;

    @Column
    private String avatar;

    @Column
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )
    )
    private List<Role> roles = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Bangkok")
    @JsonProperty("created_date")
    private Instant createdDate = Instant.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Bangkok")
    @JsonProperty("updated_date")
    private Instant updatedDate;

}
