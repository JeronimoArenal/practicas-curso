package org.example4;

public class VehiculoConverter extends AbstractConverter<Vehiculo, VehiculoDTO> {

    @Override
    public VehiculoDTO fromEntity(Vehiculo entity) {
        if (entity == null) return null;

        // Determinamos el tipo de vehículo para el DTO
        String tipo = "Desconocido";
        if (entity instanceof VehiculoAereo) tipo = "Aéreo";
        else if (entity instanceof VehiculoTerrestre) tipo = "Terrestre";
        else if (entity instanceof VehiculoMaritimo) tipo = "Marítimo";

        // Retornamos el Record VehiculoDTO
        return new VehiculoDTO(
                entity.getId(),
                entity.getMarca() + " " + entity.getModelo(), // Aplanamos Marca + Modelo
                entity.getUrl(),
                tipo
        );
    }

    @Override
    public Vehiculo fromDTO(VehiculoDTO dto) {
        // Como Vehiculo es una clase abstracta, no podemos hacer un "new Vehiculo()"
        // Normalmente para crear vehículos nuevos usaríamos conversores específicos
        // (como CocheConverter), pero para este ejemplo lo dejamos así:
        throw new UnsupportedOperationException("No se puede convertir un DTO a un Vehículo genérico (es abstracto)");
    }
}
