package org.example.microgestormonopatin.Dto;
import org.example.microgestormonopatin.Entity.Monopatin;

public class MonopatinDto {
    private Long id;
    private double kms;
    private boolean disponible;
    private String tiempoDeUso;              // a chequear
    private int cantViajes;
    private String gps;                         // a chequear
    private String tiempoPausa;
    private double mantenimientoTiempoUso;
    private double mantenimientoKms;
    private long id_parada;

    public MonopatinDto(Monopatin monopatin) {
        this.id = monopatin.getId();
        this.kms = monopatin.getKms();
        this.disponible = monopatin.isDisponible();
        this.tiempoDeUso = monopatin.getTiempo_de_uso();
        this.cantViajes = monopatin.getCant_viajes();
        this.tiempoPausa = monopatin.getTiempo_pausa();
        this.mantenimientoKms = monopatin.getMantenimiento_kms();
        this.mantenimientoTiempoUso = monopatin.getMantenimiento_tiempo_uso();
        this.id_parada = monopatin.getId_parada();
    }

    @Override
    public String toString() {
        return "MonopatinDto{" +
                "id=" + id +
                ", kms=" + kms +
                ", disponible=" + disponible +
                ", tiempoDeUso='" + tiempoDeUso + '\'' +
                ", cantViajes=" + cantViajes +
                ", gps='" + gps + '\'' +
                ", tiempoPausa='" + tiempoPausa + '\'' +
                ", mantenimientoTiempoUso=" + mantenimientoTiempoUso +
                ", mantenimientoKms=" + mantenimientoKms +
                ", id_parada=" + id_parada +
                '}';
    }
}


