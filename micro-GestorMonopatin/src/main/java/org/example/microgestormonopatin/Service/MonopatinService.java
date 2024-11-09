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
        return MonopatinRepository.findAll().stream().map(MonopatinDto::new).collect(Collectors.toList());
    }
    @Transactional
    public MonopatinDto findById(Long id) throws Exception {
        return MonopatinRepository.findById(id).map(MonopatinDto::new).orElse(null);
    }
    @Transactional
    public MonopatinDto save(Monopatin entity) throws Exception {
        MonopatinRepository.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (MonopatinRepository.existsById(id)) {
            MonopatinRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}


