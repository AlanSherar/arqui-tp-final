package org.example.microgestorviajes.clienteFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="Micro-GestorParadas")
public interface ParadaClient {

    @GetMapping("/{id}")
    ResponseEntity<Parada> getParadaById(@PathVariable("id") Long id);

    @GetMapping("/ubicacionX/{x}/ubicacionY/{y}")
    ResponseEntity<Parada> getByUbicacion(@PathVariable int x, @PathVariable int y);


}
