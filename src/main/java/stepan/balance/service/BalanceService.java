package stepan.balance.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import stepan.balance.model.Balance;
import stepan.balance.repository.BalanceRepository;
import stepan.balance.repository.OperationRepository;

import java.util.Optional;

@Service
public class BalanceService {

    private BalanceRepository balanceRepository;

    public BalanceService(final BalanceRepository repository) {
        balanceRepository = repository;
    }

    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }

    public Optional<Balance> getBalance(Integer userId) {
        return balanceRepository.findById(userId);
    }

    public Double getBalanceCount(Integer userId) {
        return balanceRepository.findById(userId).get().getCurrentBalance();
    }

    @Transactional
    public void putMoney(Integer userId, Double val) {
        final var putMoneyBalance = balanceRepository.findById(userId).orElseThrow();
        Double currentBalance = putMoneyBalance.getCurrentBalance();
        if (currentBalance == null) {
            currentBalance = val;
        } else currentBalance += val;
        putMoneyBalance.setCurrentBalance(currentBalance);
        balanceRepository.save(putMoneyBalance);
    }

    @Transactional
    public void takeMoney(Integer userId, Double val) {
        final var takeMoneyBalance = balanceRepository.findById(userId).orElseThrow();
        Double currentBalance = takeMoneyBalance.getCurrentBalance();
        if (currentBalance == null) {
            currentBalance = 0.0;
        } else currentBalance = currentBalance;
        if (currentBalance < val) {
            System.out.println("На счёте недостаточно средств");
        } else currentBalance -= val;
        takeMoneyBalance.setCurrentBalance(currentBalance);
        balanceRepository.save(takeMoneyBalance);
    }
}
