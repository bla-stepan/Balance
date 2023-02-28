package stepan.balance.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import stepan.balance.model.Operation;
import stepan.balance.repository.OperationRepository;

import java.time.LocalDate;
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
        setOperation.setOperationDate(Calendar.getInstance());
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
    public List<Operation> getOperationsList(Integer userId, String firstDate, String lastDate) {
        List<Operation> operationsList = null;
        LocalDate fromDate = getLocalDate(firstDate);
        LocalDate toDate = getLocalDate(lastDate);
        if (firstDate.equals(null) || lastDate.equals(null)) {
            Optional<Operation> optional = operationRepository.findById(userId);
            operationsList = optionalToList(optional);
        } else {
            operationsList = operationRepository.findAllByOperationDateBetweenAndAndUserId(userId, fromDate, toDate);
        }
        return operationsList;
    }

    //метод преобразования объект Optional в List
    public List<Operation> optionalToList(Optional<Operation> optionalOperation) {
        return optionalOperation.isPresent()
                ? Collections.singletonList(optionalOperation.get())
                : Collections.emptyList();
    }

    //получение объекта календаря
    private Calendar dateFormat(String date) {
        String[] params = date.split("/");//02.02.2023
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.YEAR, Integer.parseInt(params[2]));
        calDate.set(Calendar.MONTH, Integer.parseInt(params[1]) + 1);
        calDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(params[0]));
        return calDate;
    }
    //метод аолучения локалдата из строки
    private LocalDate getLocalDate(String date){
        String[] params = date.split("/");
        int year = Integer.parseInt(params[2]);
        int month = Integer.parseInt(params[1])+1;
        int dayOfMonth = Integer.parseInt(params[0]);
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return localDate;
    }
}
