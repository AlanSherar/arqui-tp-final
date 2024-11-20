package org.example.micromantenimiento.feignClients;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "micro-GestorMonopatin", url = "http://localhost:8094/monopatines")
public interface MonopatinFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<Monopatin> getMonopatinById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<Monopatin> updateMonopatin(@PathVariable("id") Long id, @RequestBody Monopatin monopatin);

}