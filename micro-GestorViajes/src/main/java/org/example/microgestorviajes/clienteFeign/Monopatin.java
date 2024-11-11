package org.example.microgestorviajes.clienteFeign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;


@Data
public class Monopatin {
    private Long id;
    private double kms;
    private boolean disponible;
    private double tiempo_de_uso;
    private int cant_viajes;
    private double tiempo_pausa;
    private double mantenimiento_tiempo_uso;
    private double mantenimiento_kms;
    private int ubicacionX;
    private int ubicacionY;


}
