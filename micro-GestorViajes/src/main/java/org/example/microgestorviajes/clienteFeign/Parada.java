package org.example.microgestorviajes.clienteFeign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.microgestorviajes.clienteFeign.Monopatin;

import java.util.ArrayList;

public class Parada {

    private Long id;
    private int ubicacionX;
    private int ubicacionY;

    private ArrayList<Monopatin> monopatines;

    public long getId() {
        return id;
    }

    public void addMonopatin(Monopatin monopatin){
        this.monopatines.add(monopatin);
    }
    public void deleteMonopatin(Monopatin monopatin){
        this.monopatines.remove(monopatin);
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