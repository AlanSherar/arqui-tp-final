package org.example.microgestordecuentas.controller;
import org.example.microgestordecuentas.Entity.Usuario;
import org.example.microgestordecuentas.services.MicroGestorCuentasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestorCuentas")
public class CuentaController {

    @Autowired
    private MicroGestorCuentasServices service;

    @GetMapping("/")
    public List<Usuario> getAll(){

        return service.getAll();
        /*

            {
            "id_usuario" : 2,
            "username" : "jcambareri6,
            "numero_celular" : 222222,
            "nombre" : "ddd",
            "apellido" : "dddd",
            "email" : "dddd",
            "rol" : "estudiante"
            }

         */

    }

    @PostMapping("/")
    public ResponseEntity<?> addOne(@RequestBody Usuario u){
        try{
            service.saveOne(u);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("creado");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(u);
        }
    }


}