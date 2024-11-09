package org.example.microgestormonopatin.Service;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.example.microgestormonopatin.Repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.microgestormonopatin.Dto.MonopatinDto;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository MonopatinRepository;

    public List<MonopatinDto> findAll() {
        List<Monopatin> monopatines = MonopatinRepository.findAll();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines.stream()
                .map(MonopatinDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public MonopatinDto findById(Long id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("ID proporcionado no es válido.");
        }
        return MonopatinRepository.findById(id)
                .map(MonopatinDto::new)
                .orElseThrow(() -> new Exception("El monopatín con ID " + id + " no fue encontrado."));
    }
    @Transactional
    public MonopatinDto save(Monopatin monopatin) throws Exception {
        if (monopatin == null) {
            throw new IllegalArgumentException("El monopatín proporcionado no puede ser nulo.");
        }
        if (monopatin.getId() != null && MonopatinRepository.existsById(monopatin.getId())) {
            throw new IllegalStateException("El monopatín con ID " + monopatin.getId() + " ya existe.");
        }
        Monopatin savedMonopatin = MonopatinRepository.save(monopatin);
        return new MonopatinDto(savedMonopatin);
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


