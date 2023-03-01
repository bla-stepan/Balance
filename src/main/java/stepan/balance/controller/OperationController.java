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
    OperationService operationService;

    @GetMapping("/operation")
    public Optional<Operation> getOperationById(@RequestParam Integer operationId){
        return operationService.getOperationById(operationId);
    }

    @GetMapping("/allOperations")
    public List<Operation> getAllOperations(){
        return operationService.getAllOperations();
    }

    @GetMapping("/operationsList")
    public List<Operation> getOperationsList(@RequestParam Integer userId, LocalDate firstDate, LocalDate lastDate) {
        return operationService.getOperationsList(userId, firstDate, lastDate);
    }
}
