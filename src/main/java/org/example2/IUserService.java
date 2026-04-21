package org.example2;

//El contrato final.
public interface IUserService extends IBaseService<User>{

    //SOLO  métodos específicos de User
    void printUserEmail(Long id);
}
