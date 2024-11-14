package org.example.micromantenimiento.feignClients;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "micro-GestorMonopatin" ,url = "http://localhost:8094/monopatines")
public interface MonopatinFeignClient {

    @GetMapping("/{id}")
    Monopatin getMonopatinById(@PathVariable("id") Long id);
    @PutMapping("/{id}")
    ResponseEntity<?> UpdateMonopatin (@PathVariable("id") Long id, @RequestBody Monopatin m);
}