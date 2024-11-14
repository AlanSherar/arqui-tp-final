package org.example.microgestorviajes.clienteFeign;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="micro-GestorMonopatin", url = "http://localhost:8094/monopatines/")
public interface MonopatinClient {

    @GetMapping("/{id}")
    ResponseEntity<Monopatin> getMonopatinById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<Monopatin> updateById(@PathVariable("id") Long id, @RequestBody Monopatin monopatin);
}
