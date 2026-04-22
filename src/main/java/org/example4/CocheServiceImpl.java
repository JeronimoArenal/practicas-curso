package org.example4;

import java.util.List;

public class CocheServiceImpl extends BaseService<Coche> implements ICocheService {

    // Instanciamos el mapper (Como no hay Spring, lo hacemos a mano)
    private final VehiculoConverter cocheMapper = new VehiculoConverter();

    // Nuevo método: Obtener todos los coches listos para la "web" (DTO)
    public List<VehiculoDTO> findAllDTOs() {
        List<Coche> coches = findAll(); // Usamos el método heredado del BaseService
        return cocheMapper.fromEntityList(coches); // Usamos tu método con .toList()
    }
}