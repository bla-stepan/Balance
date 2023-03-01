package stepan.balance.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stepan.balance.model.Balance;
import stepan.balance.repository.BalanceRepository;
import stepan.balance.repository.OperationRepository;
import stepan.balance.service.BalanceService;
import stepan.balance.service.OperationService;

import java.util.Optional;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;//BalanceRepository balanceRepository;

//    @Autowired
//    OperationRepository operationRepository;

    @PostMapping("/save")
    public Balance save(@RequestBody Balance balance){
        return balanceService.save(balance);//balanceRepository.save(balance);
    }

    @GetMapping("/currentBalance")
    public Optional<Balance> getBalance(@RequestParam Integer userId) {
        return balanceService.getBalance(userId);//balanceRepository.findById(userId);
    }

    @PostMapping("/put")
    public void putMoney(@RequestParam Integer userId, Double val) {
//        final var putMoneyBalance = balanceService.getBalance(userId).orElseThrow();//balanceRepository.findById(userId).orElseThrow();
//        Double currentBalance = putMoneyBalance.getCurrentBalance();//
//        if (currentBalance==null){
//            currentBalance=val;
//        } else currentBalance+=val;
//
//        putMoneyBalance.setCurrentBalance(currentBalance);
//        balanceService.save(putMoneyBalance);//balanceRepository.save(putMoneyBalance);
//        System.out.println("Пополнение счета выполнено успешно");
//
//        //делаем запись об операции
//        OperationService setOperation = new OperationService(operationRepository);
//        setOperation.setOperation(userId, 1, val.intValue());
        balanceService.putMoney(userId, val);
    }

    @PostMapping("/take")
    public void takeMoney(@RequestParam Integer userId, Double val){
//        final var takeMoneyBalance = balanceService.getBalance(userId).orElseThrow();//balanceRepository.findById(userId).orElseThrow();
//
//        Double currentBalance = takeMoneyBalance.getCurrentBalance();
//        if (currentBalance==null){
//            currentBalance=0.0;
//        } else currentBalance=currentBalance;
//
//        if (currentBalance<val){
//            System.out.println("На счёте недостаточно средств");
//        } else {
//            currentBalance-=val;
//            System.out.println("Средства успешно сняты со счёта");
//            takeMoneyBalance.setCurrentBalance(currentBalance);
//            balanceService.save(takeMoneyBalance);//balanceRepository.save(takeMoneyBalance);
//
//            //делаем запись об операции
//            OperationService setOperation = new OperationService(operationRepository);
//            setOperation.setOperation(userId, 2, val.intValue());
//        }
        balanceService.takeMoney(userId, val);
    }
}
