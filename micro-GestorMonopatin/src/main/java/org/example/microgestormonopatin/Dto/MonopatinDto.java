package org.example.microgestormonopatin.Dto;
import org.example.microgestormonopatin.Entity.Monopatin;

import java.time.LocalTime;

public class MonopatinDto {
    private Long id;
    private double kms;
    private boolean disponible;
    private LocalTime tiempoDeUso;              // a chequear
    private int cantViajes;
    private String gps;                         // a chequear
    private LocalTime tiempoPausa;
    private double mantenimientoTiempoUso;
    private double mantenimientoKms;
    private long id_parada;

    public MonopatinDto(Monopatin monopatin) {
        this.id = monopatin.getId();
        this.kms = monopatin.getKms();
        this.disponible = monopatin.isDisponible();
        this.tiempoDeUso = monopatin.getTiempoDeUso();
        this.cantViajes = monopatin.getCantViajes();
        this.gps = monopatin.getGps();
        this.tiempoPausa = monopatin.getTiempoPausa();
        this.mantenimientoKms = monopatin.getMantenimientoKms();
        this.mantenimientoTiempoUso = monopatin.getMantenimientoTiempoUso();
        this.id_parada = monopatin.getIdParada();
    }
}


