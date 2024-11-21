package org.example.microgestorparadas.Controller;

import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Service.ParadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/paradas")
public class ParadasController {

    @Autowired
    private ParadasService service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String id) {
        try {
            return service.findById(id)
                    .map(parada -> ResponseEntity.status(HttpStatus.OK).body((Object) parada))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("error", "Error. No se encuentra el objeto buscado.")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error en el servidor."));
        }
    }


    // Crear una nueva parada
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Parada parada){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(parada));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarParada(@PathVariable String id, @RequestBody Parada p) {
        try {
            Parada parada = service.updateParada(id, p);
            if (parada != null) {
                return ResponseEntity.status(HttpStatus.OK).body(parada);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parada no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }
    }

    @GetMapping("/ubicacionX/{x}/ubicacionY/{y}")
    public ResponseEntity<?> getByUbicacion(@PathVariable int x, @PathVariable int y) {
        try {
            return service.findByUbicacion(x, y)
                    .map(parada -> ResponseEntity.status(HttpStatus.OK).body((Object)parada))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("error", "No se encuentra una parada en esta ubicación")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error al buscar la parada en la ubicación.\"}");
        }
    }


}
