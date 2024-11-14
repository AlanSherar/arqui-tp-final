package org.example.microgestorviajes.clienteFeign;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class Usuario {

    private Long id_usuario;
    private String username;
    private int numero_celular;
    private String email;
    private  String nombre;
    private String apellido;
    private String rol;
    private List<CuentasAsociadas> cuentas;

    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", username='" + username + '\'' +
                ", numero_celular=" + numero_celular +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", rol='" + rol + '\'' +
                ", cuentas=" + cuentas +
                '}';
    }
}
