package org.example.micromantenimiento.models;
public class Monopatin {

    private Long id;
    private String modelo;
    private Integer kilometros;
    private Boolean disponible;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getKilometros() {
        return kilometros;
    }

    public void setKilometros(Integer kilometros) {
        this.kilometros = kilometros;
    }

    public Boolean disponible() {
        return disponible;
    }

    public void setdisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
