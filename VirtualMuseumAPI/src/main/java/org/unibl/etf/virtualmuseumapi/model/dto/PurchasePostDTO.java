package org.unibl.etf.virtualmuseumapi.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
@ToString
public class PurchasePostDTO {

    @NotNull
    private Integer userId;
    @NotNull
    private Integer tourId;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;
    @NotNull
    @Size(max = 200)
    private String firstName;
    @NotNull
    @Size(max = 200)
    private String lastName;
    @NotNull
    @Size(max = 25)
    private String cardType;
    @NotNull
    @Size(min = 4, max = 4)
    private String expirationDate;
    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;
}
