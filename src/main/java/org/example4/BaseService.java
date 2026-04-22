package org.example4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T extends Base> implements IBaseService<T> {

    // Almacenamiento en memoria usando un Map (ID -> Entidad)
    protected Map<Long, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        // 1. Regla de negocio: El nombre es obligatorio
        if (entity.getName() == null || entity.getName().isBlank()) {
            throw new DomainException("Error: El nombre es obligatorio para registrar el vehículo.");
        }

        // 2. Generación automática de URL si no existe (Lógica de tu Método 1)
        if (entity.getUrl() == null || entity.getUrl().isBlank()) {
            entity.setUrl(entity.getName().toLowerCase()
                    .trim()
                    .replace(" ", "-"));
        }

        // 3. Guardar en el "mapa"
        storage.put(entity.getId(), entity);
        System.out.println("SISTEMA: Guardado correctamente [" + entity.getClass().getSimpleName() + "]: " + entity.getName());
        return entity;
    }

    @Override
    public T findById(Long id) {
        T entity = storage.get(id);
        if (entity == null) {
            // Extraemos el nombre de la clase para un mensaje de error limpio
            String tipo = this.getClass().getSimpleName().replace("ServiceImpl", "");
            throw new ResourceNotFoundException(tipo, id);
        }
        return entity;
    }

    @Override
    public T findByUrl(String url) {
        return storage.values().stream()
                .filter(e -> url.equalsIgnoreCase(e.getUrl()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        if (!storage.containsKey(id)) {
            throw new ResourceNotFoundException("Entidad", id);
        }
        storage.remove(id);
    }
}
