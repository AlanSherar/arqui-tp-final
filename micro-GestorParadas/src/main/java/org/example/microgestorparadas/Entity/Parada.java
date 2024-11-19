package org.example.microgestorparadas.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue
    private Long id;
    private int ubicacionX;
    private int ubicacionY;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(int ubicacionY) {
        this.ubicacionY = ubicacionY;
    }

    public int getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(int ubicacionX) {
        this.ubicacionX = ubicacionX;
    }
}