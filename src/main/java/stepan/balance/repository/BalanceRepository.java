package stepan.balance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stepan.balance.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
}
