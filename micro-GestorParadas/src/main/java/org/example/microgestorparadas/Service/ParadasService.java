package org.example.microgestorparadas.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Repository.ParadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ParadasService {

    @Autowired
    private ParadasRepository repository;

    public List<Parada> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Parada> findById(Long id) throws Exception {
        return repository.findById(id);
    }


    @Transactional
    public Parada save(Parada parada) throws Exception {
        return repository.save(parada);
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }

    public Parada updateParada(Long id, Parada p) {
        Parada paradaDb = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parada no encontrada con ID: " + id));
        paradaDb.setUbicacionX(p.getUbicacionX());
        paradaDb.setUbicacionY(p.getUbicacionY());
        return repository.save(paradaDb);
    }

    public ResponseEntity<?> findByUbicacion(int x, int y){
        return repository.getByUbicacion(x, y);
    }


}