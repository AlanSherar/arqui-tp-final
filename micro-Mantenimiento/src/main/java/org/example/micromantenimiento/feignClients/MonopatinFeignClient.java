package org.example.micromantenimiento.feignClients;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microservicio-monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{id}")
    ResponseEntity<Monopatin> getMonopatinById(@PathVariable("id") Long id);
    @PutMapping ("monopatines/{id}")
    ResponseEntity<?> ActualizarMonopatin (@PathVariable("id") Long id, @RequestBody Monopatin m);
}