package org.example.microadmincuentas.Controller;

import org.apache.http.impl.bootstrap.HttpServer;
import org.example.microadmincuentas.Entities.Tarifa;
import org.example.microadmincuentas.services.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServices serviceAdmin;
    @PostMapping("/Tarifas")
    public ResponseEntity<?> DefinirTarifa(@RequestBody Tarifa t){

           return serviceAdmin.AsignarTarifa(t);

    }
    @PutMapping("/Cuentas/{id}/anularCuenta")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id){

            return serviceAdmin.anularCuenta(id);

    }




}
