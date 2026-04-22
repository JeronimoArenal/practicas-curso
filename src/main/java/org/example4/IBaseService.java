package org.example4;

// Contrato específico para nuestra jerarquía Base
public interface IBaseService<T extends Base> extends ICrudService<T> {
    T findByUrl(String url);
}