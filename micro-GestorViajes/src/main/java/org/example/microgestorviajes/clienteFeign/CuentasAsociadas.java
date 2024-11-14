package org.example.microgestorviajes.clienteFeign;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;


public class CuentasAsociadas {
    private Long id;
    private float saldo;
    private Date fechaAlta;
    List<Usuario> usuarioAsociado;
    private boolean habilitada;


}