package org.unibl.etf.virtualmuseumapi.model.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
