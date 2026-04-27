package org.example3;

import lombok.*;

import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight implements Comparable<Flight> {
    private String code;
    private String origin;
    private String destination;
    private FlightPriority priority;
    private LocalTime departureTime;


    //................................... M E T H O D S ...........................................
    @Override
    public int compareTo(Flight other) {
        // 1. Primero comparamos por nivel de prioridad del Enum
        int priorityCompare = Integer.compare(this.priority.getLevel(), other.priority.getLevel());
        if (priorityCompare != 0) {

            return priorityCompare;
        }
        // 2. Si la prioridad es la misma, comparamos por hora usando el compareTo de LocalTime
        return this.departureTime.compareTo(other.departureTime);
    }

    /** En caso de querer ordenar solo por prioridad
     *     @Override
     *     public int compareTo(Flight other) {
     *         return Integer.compare(this.priority.getLevel(), other.priority.getLevel());    // Compara por el nivel numérico del Enum
     *     }
     */

    /** En caso de querer ordenar por hora de Salida
     * @Override
     * public int compareTo(Flight other) {
     *     // Si la prioridad es diferente, manda la prioridad
     *     if (this.priority != other.priority) {
     *         return Integer.compare(this.priority.getLevel(), other.priority.getLevel());
     *     }
     *     // Si la prioridad es IGUAL, manda la hora (el más temprano primero)
     *     // Suponiendo que tienes un campo 'hora'
     *     return Integer.compare(this.hora, other.hora);
     * }
     */
}