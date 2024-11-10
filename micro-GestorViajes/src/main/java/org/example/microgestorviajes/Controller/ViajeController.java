package org.example.microgestorviajes.Controller;

import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    public  ViajeService viajeService;

    @PostMapping("/")
    public ResponseEntity<?> crearViaje(
            @RequestParam Long monopatinId,
            @RequestParam Long usuarioId,
            @RequestParam Long paradaId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.crearViaje(monopatinId, usuarioId, paradaId));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al crear un viaje");
        }
    }



}

