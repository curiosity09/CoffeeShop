package com.softclub.model.service;

import com.softclub.model.dto.CoffeeDTO;

import java.util.List;
import java.util.Optional;

public interface CoffeeService {

    List<CoffeeDTO> findAll();

    Long save(CoffeeDTO coffeeDTO);

    void update(CoffeeDTO coffeeDTO);

    void delete(CoffeeDTO coffeeDTO);

    Optional<CoffeeDTO> findById(Long id);
}
