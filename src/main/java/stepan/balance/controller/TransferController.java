package stepan.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stepan.balance.model.Transfer;
import stepan.balance.service.BalanceService;
import stepan.balance.service.OperationService;
import stepan.balance.service.TransferService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @Autowired
    BalanceService balanceService;

    @Autowired
    OperationService operationService;

    @PostMapping("/transferMoney")
    public void setTransfer(@RequestParam Integer senderId, @RequestParam Integer recipientId, @RequestParam Integer amount){
        if (balanceService.getBalanceCount(senderId)>amount) {
            balanceService.takeMoney(senderId, amount.doubleValue());
            operationService.setOperation(senderId, 2, amount);
            balanceService.putMoney(recipientId, amount.doubleValue());
            operationService.setOperation(recipientId, 1, amount);
            transferService.setTransfer(senderId, recipientId, amount);
        }else {
            balanceService.takeMoney(senderId, amount.doubleValue());
        }
    }

    @GetMapping("/transferId")
    public Optional<Transfer> getTransferById(@RequestParam Integer transferId){
        return transferService.getTransferById(transferId);
    }

    @GetMapping("/allTransfer")
    public List<Transfer> getAllTransfer(){
        return transferService.getAllTransferList();
    }
}
