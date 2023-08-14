package tdtu.vn.figure_shop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("tdtu.vn.figure_shop.domain")
@EnableJpaRepositories("tdtu.vn.figure_shop.repos")
@EnableTransactionManagement
public class DomainConfig {
}
