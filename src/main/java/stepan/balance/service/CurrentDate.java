package stepan.balance.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class CurrentDate {
    public LocalDate getCurrentLocalDate(){
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        ZoneId zoneId = timeZone == null ? ZoneId.systemDefault() : timeZone.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zoneId).toLocalDate();
    }
}
