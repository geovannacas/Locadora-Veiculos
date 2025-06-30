package com.poo.domain.interfaces;

public interface IRepository<TClass> {
    void Create(TClass entity);
    void Update(TClass entity);

    void Delete(String id);

    TClass GetById(String id);

    Iterable<TClass> GetAll();
}
