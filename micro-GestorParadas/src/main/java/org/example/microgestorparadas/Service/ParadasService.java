package org.example.microgestorparadas.Service;

import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Repository.ParadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadasService {

    @Autowired
    private ParadasRepository repository;

    public List<Parada> findAll() {
        return repository.findAll();
    }

    public Optional<Parada> findById(String id) {
        return repository.findById(id);
    }

    public Parada save(Parada parada) {
        return repository.save(parada);
    }

    public ResponseEntity<?> delete(String id) {
        Optional<Parada> parada = repository.findById(id);
        if (parada.isPresent()) {
            repository.delete(parada.get());
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }

    public Parada updateParada(String id, Parada p) {
        Parada paradaDb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parada no encontrada con ID: " + id));
        paradaDb.setUbicacionX(p.getUbicacionX());
        paradaDb.setUbicacionY(p.getUbicacionY());
        return repository.save(paradaDb);
    }

    public Optional<Parada> findByUbicacion(int x, int y) {
        return repository.findByUbicacion(x, y);
    }

}
