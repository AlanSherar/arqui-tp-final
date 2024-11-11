package org.example.microgestormonopatin.Service;

import org.example.microgestormonopatin.Clients.ParadaClient;
import org.example.microgestormonopatin.Entity.Monopatin;
import org.example.microgestormonopatin.Repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.microgestormonopatin.Dto.MonopatinDto;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonopatinService {

    @Autowired
    private ParadaClient paradaClient;

    @Autowired
    private MonopatinRepository MonopatinRepository;

    public List<Monopatin> getAll() {
        List<Monopatin> monopatines = MonopatinRepository.findAll();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines;
    }

    @Transactional
    public Monopatin findById(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID proporcionado no es válido.");
        }
        if (MonopatinRepository.findById(id).isPresent()) {
            return MonopatinRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public Monopatin save(Monopatin monopatin) throws Exception {

        if (monopatin == null) {
            System.out.println("EXCEPTION 1");
            throw new IllegalArgumentException("El monopatín proporcionado no puede ser nulo.");
        }
        if (monopatin.getId() != null && MonopatinRepository.existsById(monopatin.getId())) {
            System.out.println("EXCEPTION 2");
            throw new IllegalStateException("El monopatín con ID " + monopatin.getId() + " ya existe.");
        }

        //System.out.println(paradaClient.getById(monopatin.getId_parada()));

        Monopatin savedMonopatin = MonopatinRepository.save(monopatin);

        System.out.println(savedMonopatin);

        return savedMonopatin;
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID proporcionado no es válido.");
        }
        if (MonopatinRepository.existsById(id)) {
            MonopatinRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatín con ID " + id + " no existe");
        }
    }
}


