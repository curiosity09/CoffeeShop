package com.softclub.model.entity.coffee;

import com.softclub.model.entity.BaseEntity;
import com.softclub.model.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(schema = "coffee_shop")
public class Coffee extends BaseEntity<Long> {

    @Column(name = "coffee_grade", unique = true, nullable = false)
    private String coffeeGrade;
    @Column(name = "price_per_gram", nullable = false)
    private double pricePerGram;
    @OneToMany(mappedBy = "coffee")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coffee coffee = (Coffee) o;
        return id != null && Objects.equals(id, coffee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
