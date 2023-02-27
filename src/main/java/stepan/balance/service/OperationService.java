package stepan.balance.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.parser.ParserException;
import stepan.balance.model.Operation;
import stepan.balance.repository.BalanceRepository;
import stepan.balance.repository.OperationRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Optional<Operation> getOperationById(Integer operationId){
        return operationRepository.findById(operationId);
    }

    public List<Operation> getAllOperations(){
        List<Operation> operations = operationRepository.findAll();
        return operations;
    }

    public List<Operation> getOperationsList(Integer userId, String fromDate, String toDate) {

        List<Operation> operations = operationRepository.findAllById(Integer userId, )
//        Stream<Operation> operationList = operations.stream()
//                .filter(d1 -> Long.parseLong(String.valueOf(d1.getOperationDate().getTime())) >=  Long.parseLong(String.valueOf(dateFormat(fromDate).getTime())))
//                .filter(d2 -> Long.parseLong(String.valueOf(d2.getOperationDate().getTime())) >=  Long.parseLong(String.valueOf(dateFormat(toDate).getTime())))
//                .collect(Collectors.toList());
    }

    //получение объекта календаря
    private Calendar dateFormat(String date) {
        String[] params = date.split("/");
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.YEAR, Integer.parseInt(params[2]));
        calDate.set(Calendar.MONTH, Integer.parseInt(params[1])+1);
        calDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(params[0]));
        return calDate;
    }
}
