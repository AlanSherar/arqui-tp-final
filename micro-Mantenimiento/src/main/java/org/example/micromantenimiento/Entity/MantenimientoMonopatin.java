package org.example.micromantenimiento.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MantenimientoMonopatin {
    @Id
    private Long id;
    private Long monopatinId;
    private String descripcion;
    private String fechaMantenimiento;

}