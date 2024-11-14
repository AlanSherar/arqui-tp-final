package org.example.microgestorviajes.clienteFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-GestorAdmin", url = "http://localhost:8091/admin")
public interface AdminClient {

    @GetMapping("/tarifa/{idTarifa}")
    Tarifa obtenerTarifa(@PathVariable("idTarifa") Long idTarifa);
}