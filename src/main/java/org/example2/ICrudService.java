package org.example2;

import java.util.List;
import java.util.Optional;

//Contrato universal (save, delete, find). El nivel más alto
public interface ICrudService<T> {
    T save(T entity);
    T findById(Long id) throws ResourceNotFoundException; // Ahora avisa que puede fallar
    //Optional<T> findById(Long id);          //Podemos usar optional en vez de la exception (más moderno)
    List<T> findAll();
    void delete(Long id);
}
