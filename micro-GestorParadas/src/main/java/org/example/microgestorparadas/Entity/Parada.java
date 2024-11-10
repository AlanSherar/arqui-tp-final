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
    @OneToMany
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