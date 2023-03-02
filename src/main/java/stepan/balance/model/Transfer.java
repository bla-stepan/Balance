package stepan.balance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="Transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;

    @Column(name="transferDate")
    private LocalDate transferDate;

    @Column(name="senderId")
    private Integer senderId;

    @Column(name="recipientId")
    private Integer recipientId;

    @Column(name = "transferAmoutn")
    private Integer transferAmount;
}
