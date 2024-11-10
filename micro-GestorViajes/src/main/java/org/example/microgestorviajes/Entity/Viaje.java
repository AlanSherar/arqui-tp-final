package org.example.microgestorviajes.Entity;
import lombok.Data;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Data
public class Viaje {

    @Id
    private Long id;

    private Long monopatinId;
    private Long usuarioId;
    private Long paradaId;
    private LocalDate fecha;
    private int horaInicio;
    private double precio;
    private int tiempoDePausa;

    public Viaje(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonopatinId() {
        return monopatinId;
    }

    public void setMonopatinId(Long monopatinId) {
        this.monopatinId = monopatinId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getParadaId() {
        return paradaId;
    }

    public void setParadaId(Long paradaId) {
        this.paradaId = paradaId;
    }

    public int getTiempoDePausa() {
        return tiempoDePausa;
    }

    public void setTiempoDePausa(int tiempoDePausa) {
        this.tiempoDePausa = tiempoDePausa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}



