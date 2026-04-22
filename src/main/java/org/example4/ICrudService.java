package org.example4;

import java.util.List;

// Contrato básico universal
public interface ICrudService<T> {
    T save(T entity);
    T findById(Long id);
    List<T> findAll();
    void delete(Long id);
}