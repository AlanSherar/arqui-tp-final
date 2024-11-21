package org.example.microgestorparadas.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paradas")
public class Parada {

    @Id
    private String id;
    private String nombre;
    private String ubicacionX;
    private String ubicacionY;


    public Parada(String id, String nombre, String ubicacionY, String ubicacionX) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacionY = ubicacionY;
        this.ubicacionX = ubicacionX;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(String ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public String getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(String ubicacionY) {
        this.ubicacionY = ubicacionY;
    }
}
