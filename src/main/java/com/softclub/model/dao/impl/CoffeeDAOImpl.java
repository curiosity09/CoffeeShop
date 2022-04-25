package com.softclub.model.dao.impl;

import com.softclub.model.dao.CoffeeDAO;
import com.softclub.model.entity.coffee.Coffee;
import com.softclub.model.entity.coffee.Coffee_;
import com.softclub.model.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Slf4j
public class CoffeeDAOImpl extends BaseDAOImpl<Long, Coffee> implements CoffeeDAO {

    @Override
    public Optional<Coffee> findCoffeeByGrade(String coffeeGrade) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Coffee> criteria = cb.createQuery(Coffee.class);
            Root<Coffee> root = criteria.from(Coffee.class);
            Optional<Coffee> optionalCoffee = session
                    .createQuery(criteria.select(root).where(cb.equal(root.get(Coffee_.coffeeGrade), coffeeGrade)))
                    .getResultList().stream().findFirst();
            log.debug(LoggerUtil.ENTITY_WAS_FOUND_IN_DAO, optionalCoffee);
            return optionalCoffee;
        }
    }
}
