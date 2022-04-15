package com.example.epamprojectmvc.dao;

import com.example.epamprojectmvc.entity.AbstractEntity;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    boolean insert(T t);

    boolean delete(T t);

    List<T> findAll();

    T update(T t);
}
