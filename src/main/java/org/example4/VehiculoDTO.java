package org.example4;

public record VehiculoDTO(
        Long id,
        String nombreCompleto, // Fusión de Marca + Modelo
        String url,
        String tipoVehiculo    // Ejemplo: "Avion", "Coche"
) {}
