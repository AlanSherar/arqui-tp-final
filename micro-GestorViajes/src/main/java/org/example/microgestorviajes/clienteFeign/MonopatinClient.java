package org.example.microgestorviajes.clienteFeign;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="micro-GestorMonopatin")
public interface MonopatinClient {

    @GetMapping("/monopatines/{id}")
    ResponseEntity<Monopatin> getMonopatinById(@PathVariable("id") Long id);

}
