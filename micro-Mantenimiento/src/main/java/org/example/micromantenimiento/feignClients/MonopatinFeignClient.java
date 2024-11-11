package org.example.micromantenimiento.feignClients;
import org.example.micromantenimiento.models.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microservicio-monopatin")
public interface MonopatinFeignClient {

    @GetMapping("/monopatines/{id}")
    Monopatin getMonopatinById(@PathVariable("id") Long id);
}