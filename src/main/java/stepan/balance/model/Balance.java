package stepan.balance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name="balance")
public class Balance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
    private Integer id;

//    @Column(name="userId")
//    private String userId;

    @Column(name="currentBalance")
    private Double currentBalance;
}
