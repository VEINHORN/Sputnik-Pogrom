package com.sputnikpogrom.db.dao;

import java.util.List;

/**
 * Created by veinhorn on 27.10.15.
 */
public interface DAO<T> {
    //List<T> findAll();
    void insert(T t);
    //void update(T t);
    //void delete(T t);
}
