package com.softclub.model.entity.order;

import com.softclub.model.entity.BaseEntity;
import com.softclub.model.entity.coffee.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(schema = "coffee_shop")
public class Order extends BaseEntity<Long> {

    @Column(name = "deliveryType", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_id")
    @ToString.Exclude
    private Coffee coffee;
    @Column(name = "grams_amount", nullable = false)
    private int gramsAmount;
    @Column(name = "delivery_start_time", nullable = false)
    private LocalTime deliveryStartTime;
    @Column(name = "delivery_end_time", nullable = false)
    private LocalTime deliveryEndTime;
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
    @Column(name = "final_price", nullable = false)
    private double finalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
