package org.example.microgestorviajes.Repository;

import org.example.microgestorviajes.Entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ViajeRepository extends JpaRepository<Viaje, Long> {

}