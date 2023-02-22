package stepan.balance.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;

    @Column(name="userId")
    private Integer userId;

    @Column(name="operationType")
    private Integer operationType;

    @Column(name = "operationAmoutn")
    private Integer operationAmount;
}