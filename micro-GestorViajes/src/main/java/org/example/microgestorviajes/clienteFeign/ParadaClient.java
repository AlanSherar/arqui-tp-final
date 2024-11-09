package org.example.microgestorviajes.clienteFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="Micro-GestorParadas")
public interface ParadaClient {

    @GetMapping("/paradas/{id}")
    Parada getParadaById(@PathVariable("id") Long id);

}
