package org.example.microgestorparadas.Service;

import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Repository.ParadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class ParadasService {

    @Autowired
    private ParadasRepository paradasRepository;

    @Autowired
    private MonopatinFeignClient monopatinFeignClient;


    public List<Parada> findAll() {
        return paradasRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> addMonopatin(Long id , Monopatin monopatin) throws Exception {
        if (monopatinFeignClient.existsById(monopatin.getId()) && this.existsById(id)) {
            Parada parada = this.findById(id);
            paradasRepository.addMonopatin(parada,monopatin);
            return ResponseEntity.status(HttpStatus.OK).body("Monopatin añadido con exito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
        }
    }
    @Transactional
    public Parada findById(Long id) throws Exception {
        return paradasRepository.findById(id);
    }
    @Transactional
    public Parada save(Parada paradas) throws Exception {
        paradasRepository.save(paradas);
        return this.findById(paradas.getId());
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (paradasRepository.existsById(id)) {
            paradasRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}