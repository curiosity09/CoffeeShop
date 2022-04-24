package com.softclub.model.dao;

import com.softclub.model.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDAO<P extends Serializable, E extends BaseEntity<P>>{

    List<E> findAll();

    P save(E entity);

    void update(E entity);

    void delete(E entity);

    Optional<E> findById(P id);
}
