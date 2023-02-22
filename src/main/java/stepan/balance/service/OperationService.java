package stepan.balance.service;

import org.springframework.stereotype.Service;
import stepan.balance.model.Operation;
import stepan.balance.repository.BalanceRepository;
import stepan.balance.repository.OperationRepository;

@Service
public class OperationService {

    private OperationRepository operationRepository;

    public OperationService(final OperationRepository repository) {
        operationRepository = repository;
    }

    public void setOperation(Integer userId, Integer operationType, Integer operationAmount) {
        Operation setOperation = new Operation();
        setOperation.setUserId(userId);
        setOperation.setOperationType(operationType);
        setOperation.setOperationAmount(operationAmount);
        operationRepository.save(setOperation);
        System.out.println("Запись об операции успешно добавлена");
    }
}
