package stepan.balance.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stepan.balance.model.Balance;
import stepan.balance.repository.BalanceRepository;
import stepan.balance.repository.OperationRepository;
import stepan.balance.service.OperationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    OperationRepository operationRepository;

    @PostMapping("/save")
    public Balance save(@RequestBody Balance balance){
        return balanceRepository.save(balance);
    }

    @GetMapping("/currentBalance")
    public Optional<Balance> getBalance(@RequestParam Integer userId) {
        return balanceRepository.findById(userId);
    }

    @PostMapping("/put")
    public void putMoney(@RequestParam Integer userId, Double val) {
        final var putMoneyBalance = balanceRepository.findById(userId).orElseThrow();
        Double currentBalance = putMoneyBalance.getCurrentBalance();//
        if (currentBalance==null){
            currentBalance=val;
        } else currentBalance+=val;

        putMoneyBalance.setCurrentBalance(currentBalance);
        balanceRepository.save(putMoneyBalance);
        System.out.println("Пополнение счета выполнено успешно");

        //делаем запись об операции
        OperationService setOperation = new OperationService(operationRepository);
        setOperation.setOperation(userId, 1, val.intValue());
    }

    @PostMapping("/take")
    public void takeMoney(@RequestParam Integer userId, Double val){
        final var takeMoneyBalance = balanceRepository.findById(userId).orElseThrow();

        Double currentBalance = takeMoneyBalance.getCurrentBalance();
        if (currentBalance==null){
            currentBalance=0.0;
        } else currentBalance=currentBalance;

        if (currentBalance<val){
            System.out.println("На счёте недостаточно средств");
        } else {
            currentBalance-=val;
            System.out.println("Средства успешно сняты со счёта");
            takeMoneyBalance.setCurrentBalance(currentBalance);
            balanceRepository.save(takeMoneyBalance);

            //делаем запись об операции
            OperationService setOperation = new OperationService(operationRepository);
            setOperation.setOperation(userId, 2, val.intValue());
        }
    }
}
