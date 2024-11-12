package org.example.microgestorviajes.Service;
import jakarta.persistence.EntityNotFoundException;
import org.example.microgestorviajes.Entity.Viaje;
import org.example.microgestorviajes.Repository.ViajeRepository;
import org.example.microgestorviajes.clienteFeign.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.microgestorviajes.clienteFeign.ParadaClient;

import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private MonopatinClient monopatinClient;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private ParadaClient paradaClient;
    @Autowired
    private ViajeRepository viajeRepository;

    public List<Viaje> getAll(){
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes;
    }

    public Viaje crearViaje(Viaje v) {
        return viajeRepository.save(v);
    }

    public Viaje getById(long id) {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
    }

    public Viaje updateViaje(long id, Viaje v) {
        Viaje viajeBd = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con ID: " + id));
        viajeBd.setFecha(v.getFecha());
        viajeBd.setHoraInicio(v.getHoraInicio());
        viajeBd.setPrecio(v.getPrecio());
        viajeBd.setTiempoDePausa(v.getTiempoDePausa());
        return  viajeRepository.save(viajeBd);
    }

    public ResponseEntity<?> finalizarViaje(Long id, int ubicacionX, int ubicacionY){
        Viaje viaje =viajeRepository.findById(id).orElseThrow( () ->  new EntityNotFoundException("Error no existe el viaje") );
        ResponseEntity<Parada> responseParada = paradaClient.getByUbicacion(ubicacionX,ubicacionY);
        // Esta en parada?
        if( responseParada.getStatusCode()== HttpStatus.OK){
            // finalizar viaje
            viaje.setEnCurso(false);
            Monopatin monopatin = monopatinClient.getMonopatinById(viaje.getMonopatinId()).getBody();
            monopatin.setUbicacionX(ubicacionX);
            monopatin.setUbicacionY(ubicacionY);

            ResponseEntity<Monopatin> res = monopatinClient.updateById(monopatin.getId(), monopatin);
            if(res.getStatusCode() != HttpStatus.OK){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : Ocurrio un error inesperado. No se pudo actualizar el monopatin "+ monopatin.getId());
            }


            // calcular el costo total
        }
    }

    public void deleteViajeByID(long id) {
        if (!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada: " + id);
        }
        viajeRepository.deleteById(id);
    }

}

