package org.example.microgestorviajes.Dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ViajeDto {

    private long monopatinId;
    private long usuarioId;
    private LocalDate fecha;
    private LocalTime horaInicio; // Mejor como LocalTime para representar la hora
    private double precio;
    private long paradaId;
    private int tiempoDePausa; // Tiempo en minutos

}
