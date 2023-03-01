package stepan.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stepan.balance.model.Balance;
import stepan.balance.service.BalanceService;
import stepan.balance.service.OperationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    @Autowired
    OperationService operationService;

    @PostMapping("/save")
    public Balance save(@RequestBody Balance balance){
        return balanceService.save(balance);
    }

    @GetMapping("/currentBalance")
    public Optional<Balance> getBalance(@RequestParam Integer userId) {
        return balanceService.getBalance(userId);
    }

    @PostMapping("/put")
    public void putMoney(@RequestParam Integer userId, Double val) {
        operationService.setOperation(userId, 1, val.intValue());
        balanceService.putMoney(userId, val);
    }

    @PostMapping("/take")
    public void takeMoney(@RequestParam Integer userId, Double val){
        if (balanceService.getBalanceCount(userId)>val) {
            balanceService.takeMoney(userId, val);
            operationService.setOperation(userId, 2, val.intValue());
        }else {
            balanceService.takeMoney(userId, val);
        }
    }
}
