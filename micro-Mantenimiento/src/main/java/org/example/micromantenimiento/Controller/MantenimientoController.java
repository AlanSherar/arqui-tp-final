package org.example.micromantenimiento.Controller;


import org.example.micromantenimiento.Service.MantenimientoService;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
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
}
