package org.example.microadmincuentas.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
public class Tarifa {
    @Id
    private Long idTarifa;
    private String nombreTarifa;
    private double PrecioTarifa;
    private Date FechaActualizacion;

    public Long getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Long idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public double getPrecioTarifa() {
        return PrecioTarifa;
    }

    public void setPrecioTarifa(double precioTarifa) {
        PrecioTarifa = precioTarifa;
    }

    public Date getFechaActualizacion() {
        return FechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        FechaActualizacion = fechaActualizacion;
    }
}
