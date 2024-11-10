package org.example.microgestordecuentas.controller;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestordecuentas.Entity.CuentasAsociadas;
import org.example.microgestordecuentas.Entity.Usuario;
import org.example.microgestordecuentas.services.GestorCuentasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GestorCuentas")
public class GestorCuentasController {

    @Autowired
    private GestorCuentasServices service;

    @GetMapping("/usuarios")
    public List<Usuario> getAll() {

        return service.getAll();


    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> addOne(@RequestBody Usuario u) {
        try {
            service.saveOne(u);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("creado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(u);
        }
    }

    @GetMapping("usuarios/{id}")
    public ResponseEntity<?> getUsuarioByID(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el usuario con el id " + id + " no existe ");
        }
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable long id, @RequestBody Usuario u) {
        try {
            System.out.println("Datos recibidos para actualización: " + u);
            Usuario usuario = service.updateUsuario(id, u);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de usuario no válidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }


    }

    @DeleteMapping("usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable long id) {


            try{
                service.deleteUsuarioByID(id);
                return ResponseEntity.status(HttpStatus.OK).body("usuario eliminado");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al eliminar el usuario");
            }


    }
    @GetMapping("/CuentasAsociadas")
    public ResponseEntity<?> getAllCuentasAsociadas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getAllCuentasAsociadas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error");
        }
    }
    @PostMapping("/CuentasAsociadas")
    public ResponseEntity<?> addOneCuentas(@RequestBody CuentasAsociadas c) {
        try {
            service.saveOneCuentas(c);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("creado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(c);
        }
    }
    @GetMapping("/CuentasAsociadas/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.GetCuentaById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error");
        }
    }
    @PutMapping("/CuentasAsociadas/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable long id,@RequestBody CuentasAsociadas c){
        try {
            System.out.println("Datos recibidos para actualización: " + c);
            CuentasAsociadas cuenta = service.updateCuenta(id, c);
            return ResponseEntity.status(HttpStatus.OK).body(cuenta);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con ID: " + id); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos de usuario no válidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor.");
        }
    }
    @DeleteMapping("/CuentasAsociadas/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable long id) {


        try{
            service.deleteCuentaById(id);
            return ResponseEntity.status(HttpStatus.OK).body("cuenta eliminado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al eliminar el usuario");
        }


    }
}