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
public class RegistrationDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Size(min = 12)
    private String userName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 15)
    private String password;
}
