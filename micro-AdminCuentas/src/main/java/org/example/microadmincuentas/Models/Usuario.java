package org.example.microadmincuentas.Models;

import jakarta.persistence.Id;

public class Usuario {
    private Long id_usuario;
    private String username;
    private int numero_celular;
    private String email;
    private  String nombre;
    private String apellido;
    private String rol;
}
