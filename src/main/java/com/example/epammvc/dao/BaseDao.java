package com.example.epammvc.dao;

import com.example.epammvc.entity.AbstractEntity;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws SQLException;

    public abstract boolean delete(T t);

    public abstract List<T> findAll();

    public abstract T update();
}
