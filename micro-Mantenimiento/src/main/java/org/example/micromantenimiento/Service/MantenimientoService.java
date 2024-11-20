package org.example.micromantenimiento.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.example.micromantenimiento.Entity.MantenimientoMonopatin;
import org.example.micromantenimiento.Repository.MantenimientoRepository;
import org.example.micromantenimiento.models.Monopatin;
import org.example.micromantenimiento.feignClients.MonopatinFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.io.DataInput;

@Service
public class MantenimientoService {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private MonopatinFeignClient monopatinClient;

    private final int kmsMantenimiento = 500;
    private final double tiempoMantenimiento = 200;

    public HttpStatus realizarMantenimiento(Long idMonopatin) {
        try {

            ResponseEntity<Monopatin> res = monopatinClient.getMonopatinById(idMonopatin);

            if (res.getStatusCode() != HttpStatus.OK) {
                if (res.getStatusCode() == HttpStatus.NOT_FOUND) {
                    return HttpStatus.NOT_FOUND;
                } else {
                    return HttpStatus.INTERNAL_SERVER_ERROR;
                }
            }

            Monopatin monopatin = res.getBody();

            if (monopatin.getKms() >= monopatin.getMantenimiento_kms() ||
                monopatin.getTiempo_de_uso() >= monopatin.getMantenimiento_tiempo_uso()) { // Necesita mantenimiento?

                monopatin.setMantenimiento_kms(monopatin.getKms() + kmsMantenimiento); // mantenimiento cada 500 kilometros
                monopatin.setMantenimiento_tiempo_uso(monopatin.getTiempo_de_uso() + tiempoMantenimiento); // mantenimiento cada 200 horas
                monopatin.setDisponible(true);

                ResponseEntity<Monopatin> updateResponse = monopatinClient.updateMonopatin(idMonopatin, monopatin);

            /*if (updateResponse.getStatusCode().is2xxSuccessful()) {
                return HttpStatus.OK;
            } else {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
*/
                if (updateResponse.getStatusCode() == HttpStatus.OK) {
                    return HttpStatus.OK;
                } else {
                    return HttpStatus.INTERNAL_SERVER_ERROR;
                }

            }
            return HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            System.err.println("Error al realizar mantenimiento: " + e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }
}