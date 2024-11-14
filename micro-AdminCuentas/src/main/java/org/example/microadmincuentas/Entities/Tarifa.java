package org.example.microadmincuentas.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tarifa {
    @Id
    private Long idTarifa;
    private String nombreTarifa;
    private int precio_tarifa;
    private String fecha_actualizacion;

    @Override
    public String toString() {
        return "Tarifa{" +
                "idTarifa=" + idTarifa +
                ", nombreTarifa='" + nombreTarifa + '\'' +
                ", precio_tarifa=" + precio_tarifa +
                ", fecha_actualizacion='" + fecha_actualizacion + '\'' +
                '}';
    }

}