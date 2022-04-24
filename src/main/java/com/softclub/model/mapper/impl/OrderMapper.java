package com.softclub.model.mapper.impl;

import com.softclub.model.dto.OrderDTO;
import com.softclub.model.entity.order.Order;
import com.softclub.model.mapper.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderMapper implements Mapper<Order, OrderDTO> {

    private final CoffeeMapper coffeeMapper = new CoffeeMapper();

    @Override
    public OrderDTO mapToDto(Order order) {
        if (Objects.nonNull(order)) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .coffeeDTO(coffeeMapper.mapToDto(order.getCoffee()))
                    .deliveryType(order.getDeliveryType())
                    .gramsAmount(order.getGramsAmount())
                    .deliveryStartTime(order.getDeliveryStartTime())
                    .deliveryEndTime(order.getDeliveryEndTime())
                    .deliveryDate(order.getDeliveryDate())
                    .finalPrice(order.getFinalPrice())
                    .build();
        }
        return null;
    }

    @Override
    public List<OrderDTO> mapToListDto(List<Order> orders) {
        if (Objects.nonNull(orders)) {
            List<OrderDTO> orderDTOList = new ArrayList<>();
            for (Order order : orders) {
                orderDTOList.add(mapToDto(order));
            }
            return orderDTOList;
        }
        return Collections.emptyList();
    }

    @Override
    public Order mapToEntity(OrderDTO orderDTO) {
        if (Objects.nonNull(orderDTO)) {
            Order order = Order.builder()
                    .coffee(coffeeMapper.mapToEntity(orderDTO.getCoffeeDTO()))
                    .deliveryType(orderDTO.getDeliveryType())
                    .gramsAmount(orderDTO.getGramsAmount())
                    .deliveryStartTime(orderDTO.getDeliveryStartTime())
                    .deliveryEndTime(orderDTO.getDeliveryEndTime())
                    .deliveryDate(orderDTO.getDeliveryDate())
                    .finalPrice(orderDTO.getFinalPrice())
                    .build();
            order.setId(orderDTO.getId());
            return order;
        }
        return null;
    }
}
