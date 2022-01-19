package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "TOUR")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "start", nullable = false)
    private Instant start;

    @Column(name = "duration", nullable = false)
    private Double duration;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "museumId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MuseumEntity museum;
}