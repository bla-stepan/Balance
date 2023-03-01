package stepan.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stepan.balance.model.Operation;
import stepan.balance.repository.OperationRepository;
import stepan.balance.service.OperationService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @Autowired
    //OperationRepository operationRepository;
    OperationService operationService;

    @GetMapping("/operation")
    public Optional<Operation> getOperationById(@RequestParam Integer operationId){
        return operationService.getOperationById(operationId);
    }

    @GetMapping("/allOperations")
    public List<Operation> getAllOperations(){
        //List<Operation> operations = operationService.getAllOperations();//operationRepository.findAll();
        return operationService.getAllOperations();//operations;
    }

    @GetMapping("/operationsList")
    public List<Operation> getOperationsList(@RequestParam Integer userId, LocalDate firstDate, LocalDate lastDate) {
//        List<Operation> operationsList = null;
//        if (firstDate.equals(null) || lastDate.equals(null)) {
//            Optional<Operation> optional = operationService.getOperationById(userId);//operationRepository.findById(userId);
//            operationsList = optional.isPresent() ? Collections.singletonList(optional.get()) : Collections.emptyList();
//        } else {
//            operationsList = operationService.getOperationsList(userId, firstDate, lastDate);//operationRepository.findAllByOperationDateBetweenAndAndUserId(firstDate, lastDate, userId);
//        }
        return operationService.getOperationsList(userId, firstDate, lastDate);
    }

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
}
