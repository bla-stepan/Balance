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
    public OperationService(){}

    @Transactional
    public void setOperation(Integer userId, Integer operationType, Integer operationAmount) {
        Operation setOperation = new Operation();
        setOperation.setUserId(userId);
        setOperation.setOperationType(operationType);
        setOperation.setOperationAmount(operationAmount);
        setOperation.setOperationDate(getCurrentLocalDate());//передается метод, выдающий текущую LocalDate
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
            Optional<Operation> optional = operationRepository.findById(userId);
            operationsList = optional.isPresent() ? Collections.singletonList(optional.get()) : Collections.emptyList();
        } else {
            operationsList = operationRepository.findAllByOperationDateBetweenAndAndUserId(firstDate, lastDate, userId);
        }
        return operationsList;
    }

    //метод преобразования объект Optional в List
//    public List<Operation> optionalToList(Optional<Operation> optionalOperation) {
//        return optionalOperation.isPresent()
//                ? Collections.singletonList(optionalOperation.get())
//                : Collections.emptyList();
//    }

    //получение объекта календаря
//    private Calendar dateFormat(String date) {
//        String[] params = date.split("/");
//        Calendar calDate = Calendar.getInstance();
//        calDate.set(Calendar.YEAR, Integer.parseInt(params[2]));
//        calDate.set(Calendar.MONTH, Integer.parseInt(params[1]) + 1);
//        calDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(params[0]));
//        return calDate;
//    }

    //метод аолучения локалдата из строки
//    private LocalDate getLocalDate(String date) {
//        LocalDate localDate = null;
//        if (date.isEmpty()) {
//            localDate=null;
//        } else {
//            String[] params = date.split("/");
//            localDate = LocalDate.of(Integer.parseInt(params[2]), Integer.parseInt(params[1]), Integer.parseInt(params[0]));
//        }
//        return localDate;
//    }

    private LocalDate getCurrentLocalDate(){
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
    }
}
