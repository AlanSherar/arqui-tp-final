package org.example.microadmincuentas.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "micro-GestorMonopatin" ,url = "http://localhost:8094/monopatines")
public interface monopatinFeignClient {


}
