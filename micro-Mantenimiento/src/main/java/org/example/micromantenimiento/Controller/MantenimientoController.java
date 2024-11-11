package org.example.micromantenimiento.Controller;


import org.example.micromantenimiento.Entity.MantenimientoMonopatin;
import org.example.micromantenimiento.Service.MantenimientoService;
import org.example.micromantenimiento.feignClients.MonopatinFeignClient;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    @Autowired
    private MantenimientoService mantenimientoService;


    @GetMapping("/monopatin/{id}")
    public Monopatin obtenerMonopatinPorId(@PathVariable Long id) {
        return mantenimientoService.obtenerMonopatinPorId(id);
    }
    @PostMapping("/{idMonopatin}")
    public ResponseEntity<?> RealizarMantenimiento (@PathVariable Long idMonopatin){
        try{
            mantenimientoService.realizarMantenimiento(idMonopatin);
           return  ResponseEntity.status(HttpStatus.OK).body(idMonopatin);
        }catch (Exception e){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al crear un mantenimiento");
        }
    }
}