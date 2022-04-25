package com.softclub.model.dto;

import com.softclub.model.entity.order.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class OrderDTO implements Serializable {

    private Long id;
    private CoffeeDTO coffeeDTO;
    private DeliveryType deliveryType;
    private int gramsAmount;
    @Future
    private LocalTime deliveryStartTime;
    @Future
    private LocalTime deliveryEndTime;
    @Future
    private LocalDate deliveryDate;
    private double finalPrice;
}
