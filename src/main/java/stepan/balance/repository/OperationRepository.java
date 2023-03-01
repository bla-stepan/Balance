package stepan.balance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stepan.balance.model.Operation;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findAllByOperationDateBetweenAndAndUserId(LocalDate firstDate, LocalDate lastDate, Integer userId);
    List<Operation> findAllByUserId(Integer userId);
}
