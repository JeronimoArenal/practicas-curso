package org.example2;

public class UserServiceImpl extends BaseService<User> implements IUserService{

    // Métodos únicos para User
    @Override
    public void printUserEmail(Long id) {
        User u = findById(id); // findById ya devuelve un User gracias a los genéricos
        if (u != null) {
            System.out.println("Email del usuario: " + u.getEmail());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
