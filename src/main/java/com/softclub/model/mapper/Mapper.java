package com.softclub.model.mapper;

import java.util.List;

public interface Mapper<E, D> {

    D mapToDto(E entity);

    List<D> mapToListDto(List<E> entities);

    E mapToEntity(D dto);
}
