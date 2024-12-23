package org.example.microgestormonopatin.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestormonopatin.Clients.MantenimientoClient;
import org.example.microgestormonopatin.Clients.ParadaClient;
import org.example.microgestormonopatin.Clients.ViajeClient;
import org.example.microgestormonopatin.Entity.Monopatin;
import org.example.microgestormonopatin.Repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.microgestormonopatin.Dto.MonopatinKmsDTO;
import org.example.microgestormonopatin.Dto.MonopatinTiempoDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonopatinService {

    private final int MAX_KMS = 100;

    @Autowired
    private ParadaClient paradaClient;
    @Autowired
    private MantenimientoClient mantenimientoClient;
    @Autowired
    private ViajeClient viajeClient;
    @Autowired
    private MonopatinRepository MonopatinRepository;
    @Autowired
    private MonopatinRepository monopatinRepository;

    private int CercaniaX = 30;
    private int CercaniaY = 30;

    public List<Monopatin> getAll() {
        List<Monopatin> monopatines = MonopatinRepository.findAll();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines;
    }

    public List<MonopatinKmsDTO> getMonopatinesByKms() {
        List<Monopatin> monopatines = MonopatinRepository.getMonopatinesByKms();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines.stream().map(MonopatinKmsDTO::new).toList();
    }

    public List<MonopatinTiempoDTO> getMonopatinesByPausa() {
        List<Monopatin> monopatines = MonopatinRepository.getMonopatinesByPausa();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines.stream().map(MonopatinTiempoDTO::new).toList();
    }

    public List<MonopatinTiempoDTO> getMonopatinesByNotPausa() {
        List<Monopatin> monopatines = MonopatinRepository.getMonopatinesByNotPausa();
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines.stream().map(MonopatinTiempoDTO::new).toList();
    }

    public long getMonopatinesEnOperacion() {
        Long CantMonopatines = MonopatinRepository.getMonopatinesEnOperacion();
        if (CantMonopatines == null) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        } else return CantMonopatines;
    }

    public long getMonopatinesEnMantenimiento() {
        Long CantMonopatines = MonopatinRepository.getMonopatinesEnMantenimiento();
        if (CantMonopatines == null) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        } else return CantMonopatines;
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

    public Monopatin modificarMonopatin(Long id, Monopatin monopatin) {
        try {
            if (monopatinRepository.existsById(id)) {
                System.out.println(7);
                monopatin.setId(id);
                return MonopatinRepository.save(monopatin);
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public List<Monopatin> getMonopatinesByCantidadViajes(int nroViajes, int anio){
        List<Monopatin> monopatines;
        monopatines = MonopatinRepository.findAll();

            List<Monopatin> monopatinesConCantViajes = viajeClient.getCantViajesByYear(nroViajes,monopatines, anio).getBody();
            return monopatinesConCantViajes;
        }

    public List<Monopatin> getCercanosZona(int x, int y){
        List<Monopatin> monopatines = monopatinRepository.getCercanosZona(x,y,CercaniaX,CercaniaY);
        if (monopatines.isEmpty()) {
            throw new IllegalStateException("No hay monopatines disponibles.");
        }
        return monopatines;
    }

}



