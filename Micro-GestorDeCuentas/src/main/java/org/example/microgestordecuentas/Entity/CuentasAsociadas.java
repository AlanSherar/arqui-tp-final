package org.example.microgestordecuentas.Entity;

import jakarta.persistence.CascadeType;
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
    private String fechaAlta;
    @ManyToMany(cascade = CascadeType.PERSIST)
    List<Usuario> usuarioAsociado;
    private boolean habilitada;
    public void addUsuario(Usuario U ){
        this.usuarioAsociado.add(U);
    }
    public boolean ContainsUsuario(Usuario u){
        return this.usuarioAsociado.contains(u);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }
}