package com.poo.domain.interfaces;

public interface IRepository<TClass> {
    void create(TClass entity);
    void update(TClass entity);

    void delete(String id);

    TClass getById(String id);

    Iterable<TClass> getAll();
}
