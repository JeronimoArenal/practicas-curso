package org.example2;

public class Main {

    public static void main(String[] args) {
        // --- SECCIÓN DE USUARIOS ---
        IUserService userService = new UserServiceImpl();

        try {
            User user = User.builder()
                    .id(100L)
                    .name("Alex Pro")
                    .email("alex@desarrollo.com")
                    .build();

            userService.save(user);

            // Ahora findById podría lanzar ResourceNotFoundException
            User recuperado = userService.findById(100L);
            System.out.println("URL generada automáticamente: " + recuperado.getUrl());

            userService.printUserEmail(100L);

        } catch (DomainException e) {
            System.err.println("Error en Usuario: " + e.getMessage());
        }

        // --- SECCIÓN DE PRODUCTOS ---
        IProductService productService = new ProductServiceImpl();

        try {
            Product p = Product.builder()
                    .id(1L)
                    .name("Teclado Mecanico")
                    .price(85.50)
                    .build();

            productService.save(p);

            // Búsqueda por URL
            Product encontrado = productService.findByUrl("teclado-mecanico");
            if (encontrado != null) {
                System.out.println("Producto encontrado: " + encontrado.getName() +
                        " con precio: " + encontrado.getPrice());
            }

        } catch (DomainException e) {
            System.err.println("Error en Producto: " + e.getMessage());
        }

        // --- SECCIÓN DE PRUEBAS DE ERRORES (Para ver las excepciones en acción) ---
        System.out.println("\n--- Probando manejo de errores ---");

        try {
            // Esto lanzará ResourceNotFoundException porque el ID 999 no existe
            userService.findById(999L);
        } catch (ResourceNotFoundException e) {
            System.out.println("Capturado error esperado: " + e.getMessage());
        }

        try {
            // Esto lanzará DomainException porque el nombre está vacío
            Product pInvalido = Product.builder().id(2L).name("").build();
            productService.save(pInvalido);
        } catch (DomainException e) {
            System.out.println("Capturado error de validación: " + e.getMessage());
        }

        // --- MÉTODOS GENÉRICOS GLOBALES ---
        System.out.println("\n--- Búsquedas Globales ---");
        buscarYMostrar(userService, "alex-pro");
        buscarYMostrar(productService, "teclado-mecanico");
    }


    //................................ M E T H O D S ................................
    // Este método acepta CUALQUIER servicio que maneje CUALQUIER cosa que extienda de Base.
    //  Esto es lo que se llama Polimorfismo con Genéricos. El método buscarYMostrar no sabe qué servicio le estás pasando,
    //   pero como sabe que cumple con el contrato de IBaseService, puede trabajar con él.
    public static <T extends Base> void buscarYMostrar(IBaseService<T> servicio, String url) {
        T resultado = servicio.findByUrl(url);
        if (resultado != null) {
            System.out.println("[Busqueda Global] Encontrado: " + resultado.getName());
        } else {
            System.out.println("[Busqueda Global] No se encontró nada para la URL: " + url);
        }
    }

}
