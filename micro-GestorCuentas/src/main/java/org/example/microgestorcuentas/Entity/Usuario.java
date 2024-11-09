package org.example.microgestorcuentas.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {
    @Id
    private int IdUsuario;
    private String Username;
    private int NumeroCelular;
    private String email;
    private  String nombre;
    private String apellido;
    private String rol;
    @ManyToMany(mappedBy = "usuarioAsociado")
    private List<CuentasAsociadas> cuentas;





}
