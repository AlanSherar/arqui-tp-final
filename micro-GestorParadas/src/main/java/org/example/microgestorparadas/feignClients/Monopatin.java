package org.example.microgestorparadas.feignClients;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Monopatin {

    @Id
    @GeneratedValue
    private Long id;
    private double kms;
    private boolean disponible;
    private LocalTime tiempoDeUso; // a chequear
    private int cantViajes;
    private String gps; // a chequear
    private LocalTime tiempoPausa;
    private double mantenimientoTiempoUso;
    private double mantenimientoKms;
    private long id_parada;

    public long getIdParada() {
        return id_parada;
    }
}