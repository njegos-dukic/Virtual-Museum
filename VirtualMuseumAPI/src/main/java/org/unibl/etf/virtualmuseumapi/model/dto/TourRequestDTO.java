package org.unibl.etf.virtualmuseumapi.model.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class TourRequestDTO {

    private String ticketNumber;
    private Integer userId;
}
