package org.example.microgestorviajes.Repository;

import org.example.microgestorviajes.Entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

}