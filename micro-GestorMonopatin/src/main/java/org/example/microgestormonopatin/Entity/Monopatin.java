package org.example.microgestormonopatin.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
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
    private String tiempoDeUso; // a chequear
    private int cantViajes;
    private String gps; // a chequear
    private String tiempoPausa;
    private double mantenimientoTiempoUso;
    private double mantenimientoKms;
    private long id_parada;

    public long getIdParada() {
        return id_parada;
    }

    @Override
    public String toString() {
        return "Monopatin{" +
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

    public long getId_parada() {
        return id_parada;
    }

    public void setId_parada(long id_parada) {
        this.id_parada = id_parada;
    }

    public double getMantenimientoKms() {
        return mantenimientoKms;
    }

    public void setMantenimientoKms(double mantenimientoKms) {
        this.mantenimientoKms = mantenimientoKms;
    }

    public double getMantenimientoTiempoUso() {
        return mantenimientoTiempoUso;
    }

    public void setMantenimientoTiempoUso(double mantenimientoTiempoUso) {
        this.mantenimientoTiempoUso = mantenimientoTiempoUso;
    }

    public String getTiempoPausa() {
        return tiempoPausa;
    }

    public void setTiempoPausa(String tiempoPausa) {
        this.tiempoPausa = tiempoPausa;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getCantViajes() {
        return cantViajes;
    }

    public void setCantViajes(int cantViajes) {
        this.cantViajes = cantViajes;
    }

    public String getTiempoDeUso() {
        return tiempoDeUso;
    }

    public void setTiempoDeUso(String tiempoDeUso) {
        this.tiempoDeUso = tiempoDeUso;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
