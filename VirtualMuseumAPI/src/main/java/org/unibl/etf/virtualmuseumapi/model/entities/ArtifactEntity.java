package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "ARTIFACT")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArtifactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "uri", nullable = false, length = 100)
    private String uri;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "tourId")
    private Integer tourId;
}