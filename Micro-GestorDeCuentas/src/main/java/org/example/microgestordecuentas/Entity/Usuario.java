package org.example.microgestordecuentas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Usuario {
    @Id
    private Long id_usuario;
    private String username;
    private int numero_celular;
    private String email;
    private  String nombre;
    private String apellido;
    private String rol;
    @ManyToMany(mappedBy = "usuarioAsociado")
    private List<CuentasAsociadas> cuentas;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(int numero_celular) {
        this.numero_celular = numero_celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
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
