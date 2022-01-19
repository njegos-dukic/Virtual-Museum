package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardUserEntityId implements Serializable {

    private static final long serialVersionUID = 233535107804940424L;

    @Column(name = "userId", nullable = false)
    private Integer userId;
    @Column(name = "cardNumber", nullable = false, length = 16)
    private String cardNumber;

    @Override
    public int hashCode() {
        return Objects.hash(userId, cardNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CardUserEntityId entity = (CardUserEntityId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.cardNumber, entity.cardNumber);
    }
}