package org.example.microadmincuentas.Controller;

import org.apache.http.impl.bootstrap.HttpServer;
import org.example.microadmincuentas.Entities.Tarifa;
import org.example.microadmincuentas.Models.Monopatin;
import org.example.microadmincuentas.services.AdminServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServices serviceAdmin;

    @GetMapping("/tarifas")
    public ResponseEntity <?>getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceAdmin.getAll());
        }catch (Exception E){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(E);
        }
    }
    @GetMapping("tarifas/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceAdmin.getTarifaById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @PostMapping("/tarifas")
    public ResponseEntity<?> DefinirTarifa(@RequestBody Tarifa t){

           return serviceAdmin.AsignarTarifa(t);

    }

    @DeleteMapping("/tarifas/{id}")
    public ResponseEntity<?> eliminarTarifa(@PathVariable Long id){


        return ResponseEntity.status(serviceAdmin.eliminarTarifa(id)).body("");
    }

    @PutMapping("tarifas/{id}")
    public ResponseEntity <?> modificarTarifa( @PathVariable  Long id, @RequestBody Tarifa t){
        return ResponseEntity.status(serviceAdmin.modificarTarifa(id,t)).body("");
    }
    @PutMapping("/cuentas/{id}/anularCuenta")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id){

            return serviceAdmin.anularCuenta(id);

    }
    @GetMapping("/monopatines/cantViajes/{viajes}/fecha/{fecha}")
    public  ResponseEntity<?>  getMonopatinesByCantViajes(@PathVariable int viajes, @PathVariable int fecha){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceAdmin.getMonopatinesByCantViajes(viajes,fecha));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al obtener los datos1");
        }
    }
    @GetMapping("monopatines/estadisticas")
    public ResponseEntity<?> getEstadisticas(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceAdmin.getEstadisticas());
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error ");
        }
    }




}
