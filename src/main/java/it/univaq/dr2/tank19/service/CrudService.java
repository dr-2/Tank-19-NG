package it.univaq.dr2.tank19.service;

import java.util.Set;

/**
 * @author Carlo Centofanti
 * @created 07/12/2018
 */
public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
