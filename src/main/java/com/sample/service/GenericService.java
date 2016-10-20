package com.sample.service;

import java.util.List;

import com.sample.exception.BusinessException;

public interface GenericService<E, K> {
    public void saveOrUpdate(E entity) throws BusinessException ;
    public List<E> getAll() throws BusinessException ;
    public E get(K id) throws BusinessException ;
    public void add(E entity) throws BusinessException ;
    public void update(E entity) throws BusinessException ;
    public void remove(E entity) throws BusinessException ;
}
