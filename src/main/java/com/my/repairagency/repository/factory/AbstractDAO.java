package com.my.repairagency.repository.factory;

public interface AbstractDAO<T> {
    T getById(int id);
}
