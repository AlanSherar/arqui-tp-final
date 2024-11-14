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

    @PutMapping("/verificar/{idMonopatin}")
    public ResponseEntity<?> RealizarMantenimiento(@PathVariable Long idMonopatin) {
        try {
            HttpStatus resStatus = mantenimientoService.realizarMantenimiento(idMonopatin);

            if (resStatus == HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.OK).body("Realizado el mantenimiento ");
            } else {
                return ResponseEntity.status(resStatus).body("Error al realizar mantenimiento");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al crear un mantenimiento");
        }
    }
}
