package org.unibl.etf.virtualmuseumapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
public class ArtifactDTO {

    private String type;
    private String base64;
    private String name;
    private String uri;
}
