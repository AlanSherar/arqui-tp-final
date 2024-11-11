package org.example.microgestorparadas.Entity;

import java.util.ArrayList;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.example.microgestorparadas.feignClients.Monopatin;


@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue
    private Long id;

    private String gps;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}