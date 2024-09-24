package hello.currencyconverter.application.user.infrastructure;

import hello.currencyconverter.application.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
