package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CARD")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardEntity {
    @Id
    @Column(name = "cardNumber", nullable = false, length = 16)
    private String id;

    @Column(name = "firstName", nullable = false, length = 200)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 200)
    private String lastName;

    @Column(name = "cardType", nullable = false, length = 25)
    private String cardType;

    @Column(name = "expirationDate", nullable = false)
    private String expirationDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private Double balance;

    @Column(name = "isEnabled", nullable = false)
    private Boolean isEnabled;
}