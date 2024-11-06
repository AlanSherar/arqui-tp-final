package org.example.microgestorviajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class MicroGestorViajesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroGestorViajesApplication.class, args);
    }

}
