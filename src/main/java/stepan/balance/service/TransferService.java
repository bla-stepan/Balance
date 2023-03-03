package stepan.balance.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import stepan.balance.model.Operation;
import stepan.balance.model.Transfer;
import stepan.balance.repository.TransferRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class TransferService {

    private TransferRepository transferRepository;
    //private CurrentDate currentDate;

    public TransferService(final TransferRepository repository){
        transferRepository=repository;
    }

    @Transactional
    public void setTransfer(Integer senderId, Integer recipientId, Integer amount){
        Transfer setTransfer = new Transfer();
        setTransfer.setSenderId(senderId);
        setTransfer.setRecipientId(recipientId);
        setTransfer.setTransferDate(getCurrentLocalDate());
        setTransfer.setTransferAmount(amount);
        transferRepository.save(setTransfer);
        System.out.println("Запись о трансакции успешно добавлена");
    }

    public Optional<Transfer> getTransferById(Integer transferId) {
        return transferRepository.findById(transferId);
    }

    private LocalDate getCurrentLocalDate(){
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
    }
}
