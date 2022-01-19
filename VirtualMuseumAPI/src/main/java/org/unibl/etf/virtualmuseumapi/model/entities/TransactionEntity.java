package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "TRANSACTION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "cardNumber", nullable = false)
    private String cardNumber;

    @Column(name = "tourId", nullable = false)
    private Integer tourId;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private Double amount;

    @Column(name = "isSuccess", nullable = false)
    private Boolean isSuccess = false;

    @Column(name = "transactionInfo", nullable = false)
    private String transactionInfo;

    @Column(name = "ticketNumber", length = 256)
    private String ticketNumber;
}