package org.example.microgestorviajes.Dto;
import jakarta.persistence.Id;
import java.time.LocalDate;

public class ViajeDto {

    private Monopatin monopatin;
    private Usuario usuario;
    private LocalDate fecha;
    private int horaInicio; // nose si es int
    private double precio;
    private Parada parada;
    private int tiempoDePausa; // nose si es int

}
