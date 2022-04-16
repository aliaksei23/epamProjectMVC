package com.example.epamprojectmvc.dao;

import com.example.epamprojectmvc.entity.AbstractEntity;
import com.example.epamprojectmvc.exception.DaoException;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    boolean insert(T t) throws DaoException;

    boolean delete(T t) throws DaoException;

    List<T> findAll() throws DaoException;

    T update(T t) throws DaoException;
}
