package org.example.microgestorviajes.clienteFeign;

import java.util.Date;

public class Tarifa {
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