package org.example.microgestorparadas.Repository;

import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Entity.Monopatin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParadasRepository extends JpaRepository<Parada, Long> {


    public void addMonopatin(Parada parada,Monopatin monopatin){
        this.parada.addMonopatin(monopatin);

    }
}
