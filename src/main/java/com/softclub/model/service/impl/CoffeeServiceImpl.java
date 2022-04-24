package com.softclub.model.service.impl;

import com.softclub.model.dao.CoffeeDAO;
import com.softclub.model.dao.impl.CoffeeDAOImpl;
import com.softclub.model.dto.CoffeeDTO;
import com.softclub.model.mapper.impl.CoffeeMapper;
import com.softclub.model.service.CoffeeService;
import com.softclub.model.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class CoffeeServiceImpl implements CoffeeService {

    private final CoffeeDAO coffeeDAO = new CoffeeDAOImpl();
    private final CoffeeMapper coffeeMapper = new CoffeeMapper();

    @Override
    public List<CoffeeDTO> findAll() {
        List<CoffeeDTO> coffeeDTOList = coffeeMapper.mapToListDto(coffeeDAO.findAll());
        log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_SERVICE, coffeeDTOList);
        return coffeeDTOList;
    }

    @Override
    public Long save(CoffeeDTO coffeeDTO) {
        if (Objects.nonNull(coffeeDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_SAVED_IN_SERVICE, coffeeDTO);
            return coffeeDAO.save(coffeeMapper.mapToEntity(coffeeDTO));
        }
        return null;
    }

    @Override
    public void update(CoffeeDTO coffeeDTO) {
        if (Objects.nonNull(coffeeDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_UPDATED_IN_SERVICE, coffeeDTO);
            coffeeDAO.update(coffeeMapper.mapToEntity(coffeeDTO));
        }
    }

    @Override
    public void delete(CoffeeDTO coffeeDTO) {
        if (Objects.nonNull(coffeeDTO)) {
            log.debug(LoggerUtil.ENTITY_WAS_DELETED_IN_SERVICE, coffeeDTO);
            coffeeDAO.delete(coffeeMapper.mapToEntity(coffeeDTO));
        }
    }

    @Override
    public Optional<CoffeeDTO> findById(Long id) {
        if (Objects.nonNull(id)) {
            CoffeeDTO coffeeDTO = coffeeMapper.mapToDto(coffeeDAO.findById(id).orElse(null));
            log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_SERVICE_BY, coffeeDTO, id);
            return Optional.of(coffeeDTO);
        }
        return Optional.empty();
    }
}
