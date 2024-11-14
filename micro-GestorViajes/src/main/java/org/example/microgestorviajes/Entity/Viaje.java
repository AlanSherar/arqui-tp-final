package org.example.microgestorviajes.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Data
public class Viaje {

    @Id
    private Long id;

    private Long monopatinId;
    private Long usuarioId;
    private Long paradaId;
    private Long TarifaId;
    private Date fecha;
    private int horaInicio;
    private double precio;
    private int tiempoDePausa;
    private boolean enCurso;

    public Viaje(){}

    public Long getTarifaId() {
        return TarifaId;
    }

    public boolean getEnCurso(){
        return enCurso;
    }

    public void setEnCurso(boolean c){
        enCurso = c;
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}



