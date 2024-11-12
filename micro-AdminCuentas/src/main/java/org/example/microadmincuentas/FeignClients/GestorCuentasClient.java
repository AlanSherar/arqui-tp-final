package org.example.microadmincuentas.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@Service
@FeignClient(name="micro-GestorMonopatin")
public interface GestorCuentasClient {

    @GetMapping("/GestorCuentas/{id}")
    Usuario getMonopatinById(@PathVariable("id") Long id);
    @DeleteMapping("/GestorCuentas/CuentasAsociadas/{id}")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id);

}

