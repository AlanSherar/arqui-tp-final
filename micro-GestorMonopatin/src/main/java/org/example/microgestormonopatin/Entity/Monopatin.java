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
    private String tiempo_de_uso; // a chequear
    private int cant_viajes;
    private String tiempo_pausa;
    private double mantenimiento_tiempo_uso;
    private double mantenimiento_kms;
    private Long id_parada;

    @Override
    public String toString() {
        return "Monopatin{" +
                "id=" + id +
                ", kms=" + kms +
                ", disponible=" + disponible +
                ", tiempo_de_uso='" + tiempo_de_uso + '\'' +
                ", cant_viajes=" + cant_viajes +
                ", tiempo_pausa='" + tiempo_pausa + '\'' +
                ", mantenimiento_tiempo_uso=" + mantenimiento_tiempo_uso +
                ", mantenimiento_kms=" + mantenimiento_kms +
                ", id_parada=" + id_parada +
                '}';
    }


}
