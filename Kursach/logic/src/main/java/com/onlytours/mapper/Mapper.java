package com.onlytours.mapper;

import org.dom4j.tree.AbstractEntity;
import com.onlytours.dto.BaseDto;


public abstract class Mapper<E extends AbstractEntity, D extends BaseDto> {

   abstract E toEntity(D dto);

    abstract D toDto(E entity);
}
