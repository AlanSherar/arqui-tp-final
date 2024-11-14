package org.example.microgestorparadas.Entity;

import java.util.ArrayList;
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
    private int  ubicacionX;
    private int ubicacionY;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}