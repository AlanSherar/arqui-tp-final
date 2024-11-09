package org.example.microgestormonopatin.Dto;

import java.time.LocalTime;

public class MonopatinDto {
    private int id;
    private double kms;
    private boolean disponible;
    private LocalTime tiempoDeUso; // a chequear
    private int cantViajes;
    private String gps; // a chequear
    private LocalTime tiempoPausa;
    private double mantenimientoTiempoUso;
    private double mantenimientoKms;
    private long id_parada;
}

