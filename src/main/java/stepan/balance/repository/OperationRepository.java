package stepan.balance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stepan.balance.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
