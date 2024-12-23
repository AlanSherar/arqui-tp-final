package org.example.microgestorviajes.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Service.ViajeService;
import org.example.microgestorviajes.clienteFeign.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    public  ViajeService viajeService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(viajeService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el usuario con el id " + id + " no existe ");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> crearViaje(@RequestBody Viaje v ){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.crearViaje(v));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al crear un viaje");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateViaje(@PathVariable long id, @RequestBody Viaje v) {
        try {
            System.out.println("Datos recibidos para actualización: " + v);
            Viaje viaje = viajeService.updateViaje(id, v);
            return ResponseEntity.status(HttpStatus.OK).body(viaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado con ID: " + id); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de viaje no válidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }
    }

    @PutMapping("/{id}/ubicacionX/{x}/ubicacionY/{y}")
    public ResponseEntity<?> finalizarViaje(@PathVariable long id, @PathVariable int ubicacionX, @PathVariable int ubicacionY){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.finalizarViaje(id, ubicacionX, ubicacionY));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al finalizar un viaje");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteViaje(@PathVariable long id) {
        try{
            viajeService.deleteViajeByID(id);
            return ResponseEntity.status(HttpStatus.OK).body("viaje eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al eliminar el viaje");
        }
    }

    @GetMapping("/cantViajes/{cantViajes}/year/{anio}")
    public ResponseEntity<?> getCantViajesByYear(@PathVariable int cantViajes, @RequestBody List<Monopatin> monopatines , @PathVariable int anio){
        try{
            List<Monopatin> monopatinesViajes =  viajeService.getViajesByYear(cantViajes,monopatines, anio);

            return ResponseEntity.status(HttpStatus.OK).body(monopatinesViajes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error en el servidor");
        }
    }

}

