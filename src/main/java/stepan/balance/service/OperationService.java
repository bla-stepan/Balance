package stepan.balance.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import stepan.balance.model.Operation;
import stepan.balance.repository.OperationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class OperationService {

    private OperationRepository operationRepository;

    public OperationService(final OperationRepository repository) {
        operationRepository = repository;
    }

    @Transactional
    public void setOperation(Integer userId, Integer operationType, Integer operationAmount) {
        Operation setOperation = new Operation();
        setOperation.setUserId(userId);
        setOperation.setOperationType(operationType);
        setOperation.setOperationAmount(operationAmount);
        setOperation.setOperationDate(getCurrentLocalDate());
        operationRepository.save(setOperation);
        System.out.println("Запись об операции успешно добавлена");
    }

    public Optional<Operation> getOperationById(Integer operationId) {
        return operationRepository.findById(operationId);
    }

    public List<Operation> getAllOperations() {
        List<Operation> operations = operationRepository.findAll();
        return operations;
    }

    //Добавлен метод для фильтрации записей (проверить правильность типа параметров дат
    public List<Operation> getOperationsList(Integer userId, LocalDate firstDate, LocalDate lastDate) {
        List<Operation> operationsList = null;
        if (firstDate==null || lastDate==null) {
            operationsList = operationRepository.findAllByUserId(userId);
        } else {
            operationsList = operationRepository.findAllByOperationDateBetweenAndAndUserId(firstDate, lastDate, userId);
        }
        return operationsList;
    }

    private LocalDate getCurrentLocalDate(){
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
    }
}
