package org.example.microgestorparadas.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorparadas.Entity.Parada;
import org.example.microgestorparadas.Service.ParadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Parada paradas){
        try{
            System.out.print("entro al save");
            return ResponseEntity.status(HttpStatus.OK).body(service.save(paradas));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarParada (@PathVariable Long id, @RequestBody Parada p){
        try{
            try {
                Parada parada = service.updateParada(id, p);
                return ResponseEntity.status(HttpStatus.OK).body(parada);
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id); // 404 Not Found
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de usuario no válidos.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error no se pueden actualizar los datos");
        }
    }

    @GetMapping("/ubicacionX/{x}/ubicacionY/{y}")
    public ResponseEntity<?> getByUbicacion(@PathVariable int x, @PathVariable int y){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findByUbicacion(x, y));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra una parada en esta ubicacion" +
                    ".\"}");
        }
    }

}
