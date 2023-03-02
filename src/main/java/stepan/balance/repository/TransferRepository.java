package stepan.balance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stepan.balance.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
