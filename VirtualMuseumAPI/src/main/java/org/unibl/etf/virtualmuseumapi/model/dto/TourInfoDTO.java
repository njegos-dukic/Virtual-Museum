package org.unibl.etf.virtualmuseumapi.model.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Validated
public class TourInfoDTO {

    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Instant start;
    @NotNull
    private Double duration;
    @NotNull
    private Double price;
    @NotNull
    private Integer museumId;
}
