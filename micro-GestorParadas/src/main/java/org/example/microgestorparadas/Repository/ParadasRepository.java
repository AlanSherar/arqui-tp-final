package org.example.microgestorparadas.Repository;


import org.example.microgestorparadas.Entity.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface ParadasRepository extends JpaRepository<Parada, Long> {

    @Query("SELECT p FROM Parada p WHERE p.ubicacionX = :x AND p.ubicacionY = :y")
    ResponseEntity<Parada> getByUbicacion(int x, int y);

}
