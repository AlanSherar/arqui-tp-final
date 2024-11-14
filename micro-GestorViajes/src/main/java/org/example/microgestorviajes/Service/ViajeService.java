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
    @Autowired
    private AdminClient adminClient;


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
        Viaje viaje = viajeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error: No existe el viaje"));
        ResponseEntity<Parada> responseParada = paradaClient.getByUbicacion(ubicacionX, ubicacionY);

        // Verificar si está en una parada válida
        if (responseParada.getStatusCode() == HttpStatus.OK){
            viaje.setEnCurso(false);

            Monopatin monopatin = monopatinClient.getMonopatinById(viaje.getMonopatinId()).getBody();
            monopatin.setUbicacionX(ubicacionX);
            monopatin.setUbicacionY(ubicacionY);

            // Actualizar la ubicación del monopatín
            ResponseEntity<Monopatin> res = monopatinClient.updateById(monopatin.getId(), monopatin);
            if (res.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Ocurrió un error inesperado. No se pudo actualizar el monopatín " + monopatin.getId());
            }
            // Obtener la tarifa desde adminClient
            Double tarifa = adminClient.obtenerTarifa(viaje.getTarifaId()).getPrecioTarifa();
            double costoTotal = calcularCosto(viaje, tarifa);
            viaje.setPrecio(costoTotal);
            viajeRepository.save(viaje);

            return ResponseEntity.ok("Viaje finalizado correctamente. Costo total: " + costoTotal);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El monopatín no está en una parada válida.");
        }
    }

    private double calcularCosto(Viaje viaje, double tarifa) {
          return 1; //El uso del monopatín es por tiempo,
//        comienza a consumirse el crédito cuando se activa el monopatín, y esto permitirá que se encienda en
//        ese momento. A partir de allí el usuario del servicio podrá utilizar el monopatín, y una vez que no lo
//        requiera más deberá dejarlo en una parada previamente establecida. En este momento selecciona la
//        opción para cortar el servicio, una vez estacionado el monopatín, finalizando el viaje. Al finalizar el viaje
//        se va a registrar la fecha y hora de finalización y los kilómetros recorridos
    }

    public void deleteViajeByID(long id) {
        if (!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada: " + id);
        }
        viajeRepository.deleteById(id);
    }

    public ResponseEntity<?> getViajesByYear(Long idMonopatin, int anio){
        ResponseEntity<Monopatin> monopatin = monopatinClient.getMonopatinById(idMonopatin);
        if(monopatin.getStatusCode() != HttpStatus.OK){
            throw new EntityNotFoundException("Error: no existe el monopatín con ID " + idMonopatin);
        }
        int cantidadViajes = viajeRepository.findViajesByYear(idMonopatin, anio);
        if(cantidadViajes <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron viajes en ese año");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cantidadViajes);
    }

}


