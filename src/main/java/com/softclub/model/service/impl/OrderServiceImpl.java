package com.softclub.model.service.impl;

import com.softclub.model.dao.OrderDAO;
import com.softclub.model.dao.impl.OrderDAOImpl;
import com.softclub.model.dto.OrderDTO;
import com.softclub.model.mapper.impl.OrderMapper;
import com.softclub.model.service.OrderService;
import com.softclub.model.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderMapper orderMapper = new OrderMapper();

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> orderDTOS = orderMapper.mapToListDto(orderDAO.findAll());
        log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_SERVICE, orderDTOS);
        return orderDTOS;
    }

    @Override
    public Long save(OrderDTO orderDTO) {
        if (Objects.nonNull(orderDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_SAVED_IN_SERVICE, orderDTO);
            return orderDAO.save(orderMapper.mapToEntity(orderDTO));
        }
        return null;
    }

    @Override
    public void update(OrderDTO orderDTO) {
        if (Objects.nonNull(orderDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_UPDATED_IN_SERVICE, orderDTO);
            orderDAO.update(orderMapper.mapToEntity(orderDTO));
        }
    }

    @Override
    public void delete(OrderDTO orderDTO) {
        if (Objects.nonNull(orderDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_DELETED_IN_SERVICE, orderDTO);
            orderDAO.delete(orderMapper.mapToEntity(orderDTO));
        }
    }

    @Override
    public Optional<OrderDTO> findById(Long id) {
        if (Objects.nonNull(id)) {
            OrderDTO orderDTO = orderMapper.mapToDto(orderDAO.findById(id).orElse(null));
            log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_SERVICE_BY, orderDTO, id);
            return Optional.of(orderDTO);
        }
        return Optional.empty();
    }
}
