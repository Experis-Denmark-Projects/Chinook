package experis.chinookspring.repository;

import experis.chinookspring.Models.Customer;

import java.util.List;

public interface CRUDRepository<T,U,S> {

    List<T> findAll();
    T findById(U id);
    int insert(T object);
    void update(U id, S parameter, S value);



}

