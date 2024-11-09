package org.example.microgestorviajes.Service;
import org.example.microgestorviajes.Entity.Viaje;
import org.springframework.stereotype.Service;
import org.example.microgestorviajes.clienteFeign.MonopatinClient;
import org.example.microgestorviajes.clienteFeign.ParadaClient;
import org.example.microgestorviajes.clienteFeign.UsuarioClient;

@Service
public class ViajeService {

    private final MonopatinClient monopatinClient;
    private final UsuarioClient usuarioClient;
    private final ParadaClient paradaClient;

    public ViajeService(MonopatinClient monopatinClient, UsuarioClient usuarioClient, ParadaClient paradaClient) {
        this.monopatinClient = monopatinClient;
        this.usuarioClient = usuarioClient;
        this.paradaClient = paradaClient;
    }

    public Viaje crearViaje(Long monopatinId, Long usuarioId, Long paradaId) {
        Monopatin monopatin = monopatinClient.getMonopatinById(monopatinId);
        Usuario usuario = usuarioClient.getUsuarioById(usuarioId);
        Parada parada = paradaClient.getParadaById(paradaId);
    }

}

