package com.softclub.model.mapper.impl;

import com.softclub.model.dto.CoffeeDTO;
import com.softclub.model.entity.coffee.Coffee;
import com.softclub.model.mapper.Mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CoffeeMapper implements Mapper<Coffee, CoffeeDTO> {

    @Override
    public CoffeeDTO mapToDto(Coffee coffee) {
        if (Objects.nonNull(coffee)) {
            return CoffeeDTO.builder()
                    .id(coffee.getId())
                    .coffeeGrade(coffee.getCoffeeGrade())
                    .pricePerGram(coffee.getPricePerGram())
                    .build();
        }
        return null;
    }

    @Override
    public List<CoffeeDTO> mapToListDto(List<Coffee> coffeeList) {
        if (Objects.nonNull(coffeeList)) {
            List<CoffeeDTO> coffeeDTOList = new ArrayList<>();
            for (Coffee coffee : coffeeList) {
                coffeeDTOList.add(mapToDto(coffee));
            }
            return coffeeDTOList;
        }
        return Collections.emptyList();
    }

    @Override
    public Coffee mapToEntity(CoffeeDTO coffeeDTO) {
        if (Objects.nonNull(coffeeDTO)) {
            Coffee coffee = Coffee.builder()
                    .coffeeGrade(coffeeDTO.getCoffeeGrade())
                    .pricePerGram(coffeeDTO.getPricePerGram())
                    .build();
            coffee.setId(coffeeDTO.getId());
            return coffee;
        }
        return null;
    }
}
