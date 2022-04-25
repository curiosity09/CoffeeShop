package com.softclub.model.dao;

import com.softclub.model.entity.coffee.Coffee;

import java.util.Optional;

public interface CoffeeDAO extends BaseDAO<Long, Coffee> {

    Optional<Coffee> findCoffeeByGrade(String coffeeGrade);
}
