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

}
