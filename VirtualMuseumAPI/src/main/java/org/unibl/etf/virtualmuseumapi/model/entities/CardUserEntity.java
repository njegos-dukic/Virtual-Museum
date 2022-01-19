package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CARD_USER")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardUserEntity {

    @EmbeddedId
    private CardUserEntityId id;
}