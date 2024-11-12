package org.example.microgestormonopatin.Controller;

import org.example.microgestormonopatin.Entity.Monopatin;
import org.example.microgestormonopatin.Service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinService service;

    @GetMapping( "/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>verificarEstadoMonopatin(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.verificarEstadoMonopatin(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateById(@PathVariable Long id, @RequestBody Monopatin monopatin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateById(id, monopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping( "/kms")
    public ResponseEntity<?> getMonopatinesByKms() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getMonopatinesByKms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }
    @GetMapping( "/tiempopausa")
    public ResponseEntity<?> getMonopatinesByPausa() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getMonopatinesByPausa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }
    @GetMapping( "/tiemponotpausa")
    public ResponseEntity<?> getMonopatinesByNotPausa() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getMonopatinesByNotPausa());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Monopatin monopatin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(monopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
    @GetMapping()

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
    @GetMapping("/estadisticas")
    public ResponseEntity<?> obtenerDisponiblesYEnMantenimiento() {
        try {
            long enOperacion = service.getMonopatinesEnOperacion();
            long enMantenimiento = service.getMonopatinesEnMantenimiento();
            return ResponseEntity.status(HttpStatus.OK).body(
                    "Monopatines en operación: " + enOperacion + ", Monopatines en mantenimiento: " + enMantenimiento
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
    }

}




