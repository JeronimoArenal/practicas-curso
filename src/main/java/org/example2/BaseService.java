package org.example2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Genéricos con restricciones:  (<T extends Base>), Gracias a T, esta lógica funciona igual para un Usuario que para un Producto.
public abstract class BaseService<T extends Base> implements IBaseService<T> {

    //Almacenamos de forma ficticia sin una DB con Map
    protected Map<Long, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        // 1. Validamos que el nombre exista (Requisito previo para la URL)
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new DomainException("No se puede guardar: El nombre es obligatorio para generar la URL.");
        }
        // Lógica para generar URL si no existe
        if (entity.getUrl() == null || entity.getUrl().isBlank()) {
            entity.setUrl(entity.getName().toLowerCase().replace(" ", "-"));
        }

        storage.put(entity.getId(), entity);
        System.out.println("DEBUG: Guardando entidad tipo: " + entity.getClass().getSimpleName());
        return entity;
    }

    @Override
    public T findById(Long id) {
        T entity = storage.get(id);

        if (entity == null) {
            // Obtenemos el nombre de la clase real (User, Product, etc.)
            String entityName = this.getClass().getSimpleName().replace("ServiceImpl", "");
            throw new ResourceNotFoundException(entityName, id);
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    //Podemos buscar cualquier cosa por URL
    @Override
    public T findByUrl(String url) {
        return storage.values().stream()
                .filter(entity -> url.equals(entity.getUrl()))
                .findFirst()
                .orElse(null);
    }
}
