package org.example.microgestorviajes.Controller;

import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    @Autowired
    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping("/crear")
    public Viaje crearViaje(
            @RequestParam Long monopatinId,
            @RequestParam Long usuarioId,
            @RequestParam Long paradaId) {
        return viajeService.crearViaje(monopatinId, usuarioId, paradaId);
    }
}

