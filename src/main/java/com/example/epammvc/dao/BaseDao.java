package com.example.epammvc.dao;

import com.example.epammvc.entity.AbstractEntity;
import com.example.epammvc.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;

    public abstract boolean delete(T t);

    public abstract List<T> findAll();

    public abstract T update();
}
