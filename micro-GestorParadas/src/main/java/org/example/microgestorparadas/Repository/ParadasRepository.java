package org.example.microgestorparadas.Repository;

import org.example.microgestorparadas.Entity.Parada;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ParadasRepository extends MongoRepository<Parada, String> {


    @Query("{'ubicacionX': ?0, 'ubicacionY': ?1}")
    Optional<Parada> findByUbicacion(int x, int y);

}
