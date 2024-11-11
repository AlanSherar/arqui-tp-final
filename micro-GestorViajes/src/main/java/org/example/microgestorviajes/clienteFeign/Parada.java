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
}