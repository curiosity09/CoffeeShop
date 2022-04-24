package com.softclub.model.service;

import com.softclub.model.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> findAll();

    Long save(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);

    Optional<OrderDTO> findById(Long id);
}
