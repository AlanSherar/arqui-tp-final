package org.example.microgestorviajes.clienteFeign;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class CuentasAsociadas {
    @Id
    private Long id;
    private float saldo;
    private Date fechaAlta;
    @ManyToMany
    List<Usuario> usuarioAsociado;
    private boolean habilitada;


}