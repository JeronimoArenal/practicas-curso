package org.example2;


//Añadimos métodos que solo tienen sentido para las entidades,
public interface IBaseService<T extends Base> extends ICrudService<T> {
    T findByUrl(String url);
}
