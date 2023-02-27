package stepan.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stepan.balance.model.Operation;
import stepan.balance.repository.OperationRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    @Autowired
    OperationRepository operationRepository;

    @GetMapping("/operation")
    public Optional<Operation> getOperationById(@RequestParam Integer operationId){
        return operationRepository.findById(operationId);
    }

    @GetMapping("/allOperations")
    public List<Operation> getAllOperations(){
        List<Operation> operations = operationRepository.findAll();
        return operations;
    }
}
