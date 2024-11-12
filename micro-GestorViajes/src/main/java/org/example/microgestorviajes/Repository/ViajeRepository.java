package org.example.microgestorviajes.Repository;

import org.example.microgestorviajes.Entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    @Query("select count(v) from Viaje v where v.monopatinId = :idMonopatin and extract(year from v.fecha) = :anio")
    int findViajesByYear(Long idMonopatin, int anio);

}