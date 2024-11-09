package org.example.microgestorviajes.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Viaje {

    @Id
    private Monopatin monopatin;
    private Usuario usuario;
    private LocalDate fecha;
    private int horaInicio; // nose si es int
    private double precio;
    private Parada parada;
    private int tiempoDePausa; // nose si es int


}



